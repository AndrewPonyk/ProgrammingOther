package com.ap;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.EncryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

import java.io.IOException;

public class EncryptSymmetric {

    public static void main(String[] args) throws IOException {
        EncryptResponse encryptResponse = new EncryptSymmetric().encryptSymmetric("Hello");
        new DecryptSymmetric().decryptSymmetric(encryptResponse.getCiphertext().toByteArray());
    }

    public EncryptResponse encryptSymmetric(String textToEncrypt) throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String projectId = "questionerwebapp";
        String locationId = "us-east1";
        String keyRingId = "andrew-ring";
        String keyId = "andrew-key";
        return encryptSymmetric(projectId, locationId, keyRingId, keyId, textToEncrypt);
    }

    // Encrypt data with a given key.
    public EncryptResponse encryptSymmetric(
            String projectId, String locationId, String keyRingId, String keyId, String plaintext)
            throws IOException {
        // Initialize client that will be used to send requests. This client only
        // needs to be created once, and can be reused for multiple requests. After
        // completing all of your requests, call the "close" method on the client to
        // safely clean up any remaining background resources.
        try (KeyManagementServiceClient client = KeyManagementServiceClient.create()) {
            // Build the key version name from the project, location, key ring, key,
            // and key version.
            CryptoKeyName keyVersionName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);

            // Encrypt the plaintext.
            EncryptResponse response = client.encrypt(keyVersionName, ByteString.copyFromUtf8(plaintext));
            System.out.printf("Ciphertext: %s%n", response.getCiphertext().toStringUtf8());
            return response;
        }
    }
}
