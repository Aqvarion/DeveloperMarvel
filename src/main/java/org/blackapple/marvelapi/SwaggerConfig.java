package org.blackapple.marvelapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                        .title("Marvel API")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .email("black.game.apple789@mail.ru")
                        )
                );
    }
}
