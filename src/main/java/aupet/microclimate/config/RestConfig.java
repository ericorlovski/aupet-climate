package aupet.microclimate.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfig {

    @Bean(name = "default")
    @Primary
    public RestTemplate defaultRestTemplate(RestTemplateBuilder builder) {
        return
                builder
                        .setConnectTimeout(Duration.ofSeconds(30))
                        .setReadTimeout(Duration.ofSeconds(30))
                        .build();
    }

    @Bean(name = "extended")
    public RestTemplate extendedTORestTemplate(RestTemplateBuilder builder) {
        return
                builder
                        .setConnectTimeout(Duration.ofSeconds(90))
                        .setReadTimeout(Duration.ofSeconds(90))
                        .build();
    }
}
