package com.fernandocanabarro.desafio_ze_delivery.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {

     @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Desafio Backend FCamara")
                .version("FernandoCanabarroAhnert")
                .description("Este é um projeto baseado no desafio proposto pelo Zé Delivery que é uma API de Parceiros, em que é possível criar um parceiro, consultar um parceiro por Id e consultar um parceiro por suas coordenadas (longitude e latitude), que estejam dentro de sua respectiva área de cobertura. "))
                .externalDocs(new ExternalDocumentation()
                        .description("Link Github do Desafio proposto")
                        .url("https://github.com/ab-inbev-ze-company/ze-code-challenges/blob/master/backend_pt.md"));
    }
}
