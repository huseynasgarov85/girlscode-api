package com.example.girlscodeapi;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class GirlsCodeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GirlsCodeApiApplication.class, args);
        String credentialsPath = System.getenv("GOOGLE_CREDENTIALS");
        System.out.println("GOOGLE_CREDENTIALS: " + credentialsPath);

        // Burada credentialsPath null gəlirsə, bu o deməkdir ki, mühit dəyişəni tanınmır
        if (credentialsPath == null) {
            System.out.println("Ətraf mühit dəyişəni tanınmadı.");
            return;
        }


        try {
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("/etc/secrets/gcp-credentials.json")))
                    .build()
                    .getService();

            System.out.println("Google Cloud Storage connection successful!");
        } catch (IOException e) {
            System.err.println("Failed to authenticate with Google Cloud: " + e.getMessage());
        }

    }

}