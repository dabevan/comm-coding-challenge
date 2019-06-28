package com.davidbevan.encryption

import com.google.cloud.kms.v1.CryptoKeyName
import com.google.cloud.kms.v1.KeyManagementServiceClient
import com.google.protobuf.ByteString
import org.bouncycastle.util.encoders.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import com.google.cloud.kms.v1.AsymmetricDecryptResponse
import com.google.cloud.kms.v1.AsymmetricDecryptRequest
import com.google.cloud.kms.v1.CryptoKeyVersionName




class EncryptionService(val projectId: String, val locationId: String, val keyRingId: String, val cryptoKeyId: String) {

    private val resourceName = CryptoKeyName.format(projectId, locationId, keyRingId, cryptoKeyId)

    fun encrypt(plaintext: ByteArray): ByteArray {

        KeyManagementServiceClient.create().use { client ->
            val response = client.encrypt(resourceName, ByteString.copyFrom(plaintext))

            return response.ciphertext.toByteArray()
        }
    }

    fun decrypt(cipherText: ByteArray): ByteArray {
        KeyManagementServiceClient.create().use { client ->

            val response = client.decrypt(resourceName, ByteString.copyFrom(cipherText))
            return response.plaintext.toByteArray()
        }
    }

    fun asymmetricDecrypt(encryptedText: ByteArray): ByteArray {
        KeyManagementServiceClient.create().use { keyManagementServiceClient ->
            val name = CryptoKeyVersionName.of(
                    projectId,
                    locationId,
                    keyRingId,
                    cryptoKeyId,
                    "1"
            )
            val ciphertext = ByteString.copyFrom(encryptedText)
            val request = AsymmetricDecryptRequest.newBuilder()
                .setName(name.toString())
                .setCiphertext(ciphertext)
                .build()
            return keyManagementServiceClient.asymmetricDecrypt(request).toByteArray()
        }
    }
}

class AESEncryption(private val secretKey: SecretKey) {

    private val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

    fun encrypt(content: ByteArray): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        return ByteArrayOutputStream(content.size).use { byteOut ->
            CipherOutputStream(byteOut, cipher).use { cipherOut ->
                byteOut.write(cipher.iv)
                cipherOut.write(content)
            }

            Base64.encode(byteOut.toByteArray())
        }
    }

    fun decrypt(content: ByteArray): ByteArray {

        ByteArrayInputStream(Base64.decode(content)).use { fileIn ->
            val fileIv = ByteArray(16)
            fileIn.read(fileIv)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(fileIv))

            return CipherInputStream(fileIn, cipher).use { cipherIn ->
                cipherIn.readBytes()
            }
        }
    }


}