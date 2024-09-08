package br.com.gjnv.petshop.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        String swaggerUrl = System.getenv("SWAGGER_URL");

        return new OpenAPI().servers(List.of(
                        new Server().url("https://" + swaggerUrl).description("HTTPS Server"),
                        new Server().url("http://" + swaggerUrl).description("HTTP Server")))
                .info(new Info()
                        .title("PetShop API")
                        .version("0.0.1")
                        .description("API para gerenciamento de um PetShop")
                        .termsOfService("https://github.com/Pet-Shop-GJNV/pet-shop-backend?tab=readme-ov-file")
                        .contact(new Contact().name("GJNV").url("https://github.com/Pet-Shop-GJNV/pet-shop-backend"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

