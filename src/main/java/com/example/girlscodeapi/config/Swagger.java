package com.example.girlscodeapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("https://girlscode-api-1.onrender.com");
        server.setDescription("Deployed Server");

        return new OpenAPI().servers(List.of(server));
    }
}

