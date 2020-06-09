package com.ap;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

import java.io.IOException;

public class DecryptSymmetric {


    public void decryptSymmetric(byte[] ciphertext) throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String projectId = "questionerwebapp";
        String locationId = "us-east1";
        String keyRingId = "andrew-ring";
        String keyId = "andrew-key";
        decryptSymmetric(projectId, locationId, keyRingId, keyId, ciphertext);
    }

    // Decrypt data that was encrypted using a symmetric key.
    public void decryptSymmetric(
            String projectId, String locationId, String keyRingId, String keyId, byte[] ciphertext)
            throws IOException {
        // Initialize client that will be used to send requests. This client only
        // needs to be created once, and can be reused for multiple requests. After
        // completing all of your requests, call the "close" method on the client to
        // safely clean up any remaining background resources.
        try (KeyManagementServiceClient client = KeyManagementServiceClient.create()) {
            // Build the key version name from the project, location, key ring, and
            // key.
            CryptoKeyName keyName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);

            // Decrypt the response.
            DecryptResponse response = client.decrypt(keyName, ByteString.copyFrom(ciphertext));
            System.out.printf("Plaintext: %s%n", response.getPlaintext().toStringUtf8());
        }
    }
}
