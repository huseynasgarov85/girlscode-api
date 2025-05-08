package com.example.girlscodeapi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class StorageConfig {

    @Value("${gcp.credentials.location}")
    private String credentialsPath;

    @Bean
    public Storage storage() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(credentialsPath.replace("classpath:", "")).getInputStream());
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
