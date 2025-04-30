package com.example.girlscodeapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "GirlsCode API",
                version = "v1",
                description = "Backend API documentation for GirlsCode Project"
        ),
        servers = {
                @Server(url = "https://girlscode-api-1.onrender.com", description = "Render Production Server"),
                @Server(url = "http://localhost:8080", description = "Local Development Server")
        }
)
public class Config {
    // No need to add anything here
}
