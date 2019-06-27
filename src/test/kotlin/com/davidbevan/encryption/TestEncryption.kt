package com.davidbevan.encryption

import io.kotlintest.specs.StringSpec
import java.nio.charset.Charset
import java.util.*

class TestEncryption: StringSpec() {

    val encryptionService = EncryptionService()

    init {
        "test encrypt then decrypt string" {
            val startString = "Hello World"
            val encryptedString = encryptionService.encrypt(startString.toByteArray())
            val decryptedString = String(encryptionService.decrypt(encryptedString))

            println("startString=\"$startString\"\n")

                for (b in encryptedString) {
                    val st = String.format("%02X", b)
                    print(st)
                }

            println("\n\ndecryptedString=\"$decryptedString\"")

        }
    }
}


//                    "encryptedString=\"${Base64.getEncoder().encode(encryptedString).toString(Charset.defaultCharset())}\"\n\n" +