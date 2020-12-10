package se.techinsight.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiSpringDocConfig {

    /**
     * Documentation in OpenApi 3.0
     * http://localhost:8080/v3/api-docs
     */

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info()
                .version("1.0")
                .title("Open Feign Client")
                .description("Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
            );
    }
}
