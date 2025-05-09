package com.example.girlscodeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GirlsCodeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GirlsCodeApiApplication.class, args);
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        System.out.println("GOOGLE_APPLICATION_CREDENTIALS: " + credentialsPath);

        // Burada credentialsPath null gəlirsə, bu o deməkdir ki, mühit dəyişəni tanınmır
        if (credentialsPath == null) {
            System.out.println("Ətraf mühit dəyişəni tanınmadı.");
            return;
        }

    }

}