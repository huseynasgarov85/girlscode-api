
package com.example.girlscodeapi.util.GCP;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class GCPStorageUtil {

    public static Storage initializeStorage() {
        try {
            String credentialsJson = System.getenv("GOOGLE_CREDENTIALS");

            if (credentialsJson == null || credentialsJson.isEmpty()) {
                throw new RuntimeException("GOOGLE_CREDENTIALS environment variable is not set or empty.");
            }

            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8)));

            return StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Google Cloud Storage", e);
        }
    }
}
