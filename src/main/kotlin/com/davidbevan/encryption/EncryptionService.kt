package com.davidbevan.encryption

import com.google.cloud.kms.v1.AsymmetricDecryptRequest
import com.google.cloud.kms.v1.CryptoKeyName
import com.google.cloud.kms.v1.CryptoKeyVersionName
import com.google.cloud.kms.v1.KeyManagementServiceClient
import com.google.protobuf.ByteString
import org.bouncycastle.util.encoders.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PublicKey
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.InvalidKeySpecException
import java.security.NoSuchAlgorithmException
import java.io.IOException
import java.nio.file.Files
import java.security.PrivateKey
import java.nio.file.Files.readAllBytes
import java.nio.file.Path
import java.nio.file.Paths


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


class AsymmetricEncryptionService() {

//rsa_padding_mode:oaep
//rsa_oaep_md:sha256
//rsa_mgf1_md:sha256
    fun encrypt(data :ByteArray, publicKeyString: String): ByteArray {
        val publicKey: PublicKey = getKey(publicKeyString)!!
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-512AndMGF1Padding")
        //val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
        //val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(data)
    }


    fun getKey(key: String): PublicKey? {
        try {
            val byteKey = Base64.decode(key)
            val X509publicKey = X509EncodedKeySpec(byteKey)
            val kf = KeyFactory.getInstance("RSA")

            return kf.generatePublic(X509publicKey)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}



class EncryptionUsingExportedOpenSSLKeys {
    @Throws(IOException::class)
    fun readFileBytes(filename: String): ByteArray {
        val path = Paths.get(filename)
        return Files.readAllBytes(path)
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun readPublicKey(filename: String): PublicKey {
        val publicSpec = X509EncodedKeySpec(readFileBytes(filename))
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(publicSpec)
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun readPrivateKey(filename: String): PrivateKey {
        val keySpec = PKCS8EncodedKeySpec(readFileBytes(filename))
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePrivate(keySpec)
    }



    fun encrypt(key :PublicKey, plaintext: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

    fun decrypt(key :PrivateKey, ciphertext :ByteArray) :ByteArray {
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }

}
