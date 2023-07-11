package aupet.microclimate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${service.cors.allowed-origins}")
    private List<String> allowedOrigins;

    @Value("${service.actuator.password}")
    private String password;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/swagger/**", "/v3/api-docs/**").hasRole("SWAGGER")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/info", "/actuator/env").hasRole("ACTUATOR").and().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/aupet-climate/**",  "/actuator/**").permitAll();
                /*.and()
                .authorizeRequests()
                .anyRequest().access("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());*/
        return http.build();
    }

    //Actuator in-memory basic auth credentials
    @Bean
    public InMemoryUserDetailsManager configure(AuthenticationManagerBuilder auth) {
        UserDetails actuatorUser = User.withDefaultPasswordEncoder()
                .username("actuator")
                .password(password)
                .roles("ACTUATOR")
                .build();

        UserDetails swaggerUser = User.withDefaultPasswordEncoder()
                .username("swagger")
                .password("Agile2022")
                .roles("SWAGGER")
                .build();

        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("aupet")
                .password("Agile2022")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(swaggerUser, actuatorUser, adminUser);
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept",
                "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
