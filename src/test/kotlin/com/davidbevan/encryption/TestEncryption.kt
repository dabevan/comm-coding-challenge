//package com.davidbevan.encryption
//
//import io.kotlintest.specs.StringSpec
//import java.io.File
//import java.nio.charset.Charset
//import javax.crypto.KeyGenerator
//
//
//class TestEncryption: StringSpec() {
//
//
//
//    init {
//        "test encryption using Google KMS Symmetric Key" {
//            //WORKS FINE
//            //Just uses Google KMS symmetric key beauty_key-1 to encrypt and decrypt data.
//            val encryptionService = EncryptionService("lee-jardine-sandbox", "europe-west2", "beauty_crm_pcd", "beauty_key-1")
//            val start = "Hello World"
//            val encrypted = encryptionService.encrypt(start.toByteArray())
//            val decrypted = String(encryptionService.decrypt(encrypted))
//            printResult(start,encrypted,decrypted)
//        }
//
//
//        "test decryption using Google KMS symmetric Key" {
//            //WORKS FINE
//            //Uses the encrypted string from the symetric encyption test, decrypts it using beauty_key-1
//            val encryptionService = EncryptionService("lee-jardine-sandbox", "europe-west2", "beauty_crm_pcd", "beauty_key-1")
//            val start = "Hello World"
//            val encrypted = "0A24001DB11758F0B6FC4AC8DABC0825431A2536815A94D22391757723D691A1E8E97B539CB0123712350A0CB6AC05670D281D34FC2DB85F1213E793B9A0A876CFEFD61B83EB6E1C59C6D314291A101892F06D04B176777CB2C870395E5724".hexStringToByteArray()
//            val decrypted = String(encryptionService.decrypt(encrypted))
//            printResult(start,encrypted,decrypted)
//        }
//
//
//        "test decryption using Google KMS asymmetric Key" {
//            //WORKS FINE
//            // Covers scenario 1
//            //Trys to decrypt something that was encrypted using OpenSSL an the exported public key from beauty_key-asymmetric-decrypt
//            val encryptionService = EncryptionService("lee-jardine-sandbox", "europe-west2", "beauty_crm_pcd", "beauty_key-asymmetric-decrypt")
//            val start = "Hello David!"
//            //var encrypted was copy and pasted from hex of the file2.txt.enc which was file2.txt (Hello David!) encrypted using....
//            //openssl pkeyutl -in file2.txt -encrypt -pubin -inkey beauty_crm_pcd-beauty_key-asymmetric-decrypt-1.pub -pkeyopt rsa_padding_mode:oaep -pkeyopt rsa_oaep_md:sha256 -pkeyopt rsa_mgf1_md:sha256 > file2.txt.enc
//            val encrypted = "22C2B0444DD71DFAB639A751BDFEBBABE0D5024A7776E88790FB283082C82849D7E5BF91081ED1348E239FC5A354F234E13A50D81137F0BD16BD9502A5D8100865B24078E3D47A96013ED2CEEE3623F50768D718ABBB8E6B07118A670CF805D1FA4EFE0C3FACFC39CCEFC9BA2094BB9E3D5210005763E4032926C4A685A93C3A36B2A51DD41574792809EDCE485CFCEAD9E3F67529D1EC38B17646D7185AD83CE9696999C28B20B4632C431879D0AEDAD4CC2C674405798AEB9362736E062F27BEEE4287189B93A814F158B36CB3840ED7AC3BC2956947CDE8D488D9E1ACC100E91C3331C16D827293EB34BA2D47EBCC1628DC444CBE2FFC157E28D641F1939B304FCD473F3ADD57FD3F48937937D5F3AB563504565BF27E383E7FEDFDA277293C6C5DD8F8CE065D9C34385733AF9EAD4675E3838273B66E0CF3538F9F82F964536BA4F87620536BA419AC7DE3D38C234A328032B81B747F0F686A451BCF95996E06938CDB8F43B4E756BAFA244C45E70734AC9910C7C0FB701E3E0EE75E8085".hexStringToByteArray()
//            val decrypted = String(encryptionService.asymmetricDecrypt(encrypted))
//            printResult(start,encrypted,decrypted)
//        }
//
//
//        "test2 decryption using Google KMS asymmetric Key" {
//            //WORKS FINE
//            //Covers scenario 1
//            //Repeat test with different data
//            //Trys to decrypt something that was encrypted using OpenSSL an the exported public key from beauty_key-asymmetric-decrypt
//            val encryptionService = EncryptionService("lee-jardine-sandbox", "europe-west2", "beauty_crm_pcd", "beauty_key-asymmetric-decrypt")
//            val start = "Hello World!"
//            //var encrypted was copy and pasted from hex of the file.txt.enc which was file.txt (Hello David!) encrypted using....
//            //openssl pkeyutl -in file.txt -encrypt -pubin -inkey beauty_crm_pcd-beauty_key-asymmetric-decrypt-1.pub -pkeyopt rsa_padding_mode:oaep -pkeyopt rsa_oaep_md:sha256 -pkeyopt rsa_mgf1_md:sha256 > file.txt.enc
//            val encrypted = "46C7552A22A0A5693AABA9DFF88434CC2F5929283252318561A6980EE1C35DA47DB0FB6A45B164A1707125E5684846DE263A7D016B526BCA3D25498A618BA4814BDFEC4983DB68E19DAD9853B4B57DF056FE3A918DE840ACF3051D5FD58760256E12485E43E285CCCCD73EBCFBA3F82374D620C23615ACAE5CAAD5A9C182F9B47367B9DD44CE524C116A76B5FE3CA36A2463923AF1C9B5F44211C01B374BA85A184F647E541973B4E74281E967E8CADC24DE795AC9E26C10249112F5729D0AC6E06C887108D5F89412898145608E6A7D29099C0A6EF117B0666FE55B406AEA7A566783979E354D082B8825D2C2DD9751901757918B8337A129C6D6570A2E7282899599DEFA3EA42EB9AB238A231BFBEC1EBE5C44EF1CD0D953516605AA45015B5DD6EAA717F46CACD963E607C57F56C359FAD4099BDAB4B94F9C06CA479D40F22ABC40E2FE68176FF944E42B090C59BEEA4B771AACE2E5FA6B21C0287C9C5AD1E8FFEF646957E9CB23A532348197A6F210B81DCC49F64B02A21A61BE8B19FADC".hexStringToByteArray()
//            val decrypted = String(encryptionService.asymmetricDecrypt(encrypted))
//            printResult(start,encrypted,decrypted)
//        }
//
//
//        "test encryption using AES" {
//            //WORKS FINE
//            //Just AES encryption - don't think this is of use
//            val keyGenerator = KeyGenerator.getInstance("AES")
//            keyGenerator.init(128)
//            val secretKey = keyGenerator.generateKey()
//            val encryptionService = AESEncryption(secretKey)
//            val start = "Hello World"
//            val encrypted = encryptionService.encrypt(start.toByteArray())
//            val decrypted = String(encryptionService.decrypt(encrypted))
//            printResult(start,encrypted,decrypted)
//        }
//
//
//        "test asymmetric encryption" {
//            //WORKS OK, BUT CAN'T DECRYPT
//            //Covers scenario 2
//            //This uses the beauty_key-asymmetric-decrypt public key to encrypt "Hello World!!!" and write it to a file
//            //It runs ok, but I can't seem to use the decrypt.sh (which uses gcloud) to decrypt the file.
//            val encryptionService = AsymmetricEncryptionService()
//            val dataString = "Hello World!!!"
//            val data = dataString.toByteArray()
//            //publicKeyString is the beauty_crm_pcd-beauty_key-asymmetric-decrypt-1.pub which was exported public ket from beauty_key-asymmetric-decrypt
//            val publicKeyString = "" +
//                    "MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAu9ccvSSf3sd0zwEZdBKj\n" +
//                    "ESCDEekglF22l9jBzN68gL6aMImjOy8NP2VTCfvfoY3U+EmHMuTTk/7VFoUxsDa9\n" +
//                    "E6226Oqzh1m0ppkK3Kjeq+2aSNtqlngWsvQ8lqQO05offA1cYsigr4XwlqHse1mP\n" +
//                    "sxEmoJro/9C65tc8ylvgzS63pntUZ2149yjoUF50dHC2fGWqUTE8TQRL+16Qi4Ur\n" +
//                    "hMX47jeKxpOWFiTmUNoQwtTgEBtVBpfwLsk4+sOmCviQm1hRJtQLDiHdd4TIhHoT\n" +
//                    "d6OQnxKgJFgg6wMIQenywtJ5+CnwfHPVFIbwA9CVNGmPF8jpfKNwNHMdsTch+/D3\n" +
//                    "jMo8pm/EKsKpB9cXoM5BJ/zsCcEQPpRVaYgGDs3qVmgqct2TSN93bKVHHvQBZ8Ey\n" +
//                    "ngo/JRrQ7j1JGMhNghf3LSJJo4OoA9aEswjPUTpT3HeXHF0IKuLDMQdoJX+VAh+8\n" +
//                    "L3dVVN8UFPzgYACrWElzXDZaWSlSX9K6S9FrLkT0EPaZAgMBAAE="
//            println("\ndataString=\"$dataString\"")
//            println("\npublicKey=\"$publicKeyString\"")
//            val encryptedData = encryptionService.encrypt(data, publicKeyString)
//            print("\nencrypted=\"")
//            for (b in encryptedData) {
//                val st = String.format("%02X", b)
//                print(st)
//            }
//            print("\"\n")
//            File("/home/dabevan/Downloads/temp/encryption/kotlin.txt.enc").writeBytes(encryptedData)
//        }
//
//
//        "test other asymmetric encryption" {
//            //WORKS OK, BUT CAN'T DECRYPT
//            //Covers scenario 3
//            //This will use the public and private pems generated by OpenSSL to encrypt and decrypt the string "Hello World"
//            //and write the result into a file.
//            //The encryption and decryption seems to work, but I can't decrpt the file using OpenSSL and the private key
//            val encryptionService = EncryptionUsingExportedOpenSSLKeys()
//            val publicKey = encryptionService.readPublicKey("/home/dabevan/Downloads/temp/encryption/openSSLPublicKey.der")
//            val privateKey = encryptionService.readPrivateKey("/home/dabevan/Downloads/temp/encryption/openSSLPrivateKey.der")
//            val message = "Hello World".toByteArray(charset("UTF8"))
//            val secret = encryptionService.encrypt(publicKey, message)
//            File("/home/dabevan/Downloads/temp/encryption/another.txt.enc").writeBytes(secret)
//            val recovered_message = encryptionService.decrypt(privateKey, secret)
//            println(String(recovered_message, Charset.defaultCharset()))
//        }
//
//    }
//
//    fun String.hexStringToByteArray() = ByteArray(this.length / 2) { this.substring(it * 2, it * 2 + 2).toInt(16).toByte() }
//
//    fun printResult(start: String,encrypted: ByteArray,decrypted: String) {
//        println("\nstart=\"$start\"\n")
//
//        print("encrypted=\"")
//        for (b in encrypted) {
//            val st = String.format("%02X", b)
//            print(st)
//        }
//        print("\"\n")
//
//        println("\ndecrypted=\"$decrypted\"\n")
//
//    }
//}