package com.example.girlscodeapi.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public Storage storage() {
        // Google Cloud Storage istemcisini oluşturuyoruz
        return StorageOptions.getDefaultInstance().getService();
    }
}
