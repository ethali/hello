package com.cooperl.hello.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.adapter.ForwardedHeaderTransformer;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class OpenApiConfiguration {

    @Bean
    ForwardedHeaderTransformer forwardedHeaderTransformer() {
        return new ForwardedHeaderTransformer();
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {
    return
        route(GET("/test"), req -> ServerResponse.temporaryRedirect(URI.create("ok")).build())
        .and(
            route(GET("/ok"), req -> ServerResponse.ok().bodyValue("OK Google!"))
        );
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
            .info(
                new Info()
                    .title("API Hello")
                    .version(appVersion)
                    .description("Cette API expose différentes ressources des données de Hello")
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License().name("Apache 2.0").url("https://www.cooperl.com"))
            );
    }

}
