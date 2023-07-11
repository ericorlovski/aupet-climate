package aupet.microclimate.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)*/
@OpenAPIDefinition(
        info = @Info(title = "AUPET API", version = "v1"),
        //security = @SecurityRequirement(name = "bearerAuth"),
        servers = {
                @Server(url = "https://aupet-climate.apps.kz"),
                @Server(url = "prod")}
)

public class SwaggerConfig {

}
