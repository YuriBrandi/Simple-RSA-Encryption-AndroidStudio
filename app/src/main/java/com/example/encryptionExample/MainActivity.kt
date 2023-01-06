package com.example.encryptionExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*
import javax.crypto.Cipher

//https://www.devglan.com/java8/rsa-encryption-decryption-java

val charset = Charsets.UTF_8

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputTxt = findViewById<EditText>(R.id.inputText)

        val encBtn = findViewById<Button>(R.id.encryptBtn)
        val decBtn = findViewById<Button>(R.id.decryptBtn)

        val keys = KeyPairGen()

        //Returns a Cipher object that implements the specified transformation.
        val encryptCipher = Cipher.getInstance("RSA")

        encBtn.setOnClickListener {
            /*
                Initializes this cipher with the public key from the given certificate.
                First Param: operation mode = Encrypt.
                Second Param: Public key.
             */
            encryptCipher.init(Cipher.ENCRYPT_MODE, keys.publicKey)

            //Converts the text to an Array of Bytes using UTF-8
            val strAsByteArr = inputTxt.text.toString().toByteArray(charset)

            //Data Encryption
            val encryptedByteArr = encryptCipher.doFinal(strAsByteArr)

            //println(Arrays.toString(encryptedByteArr))
            //println(encryptedByteArr.size)

            /*
                Transforms the array of encrypted bytes to Base64 text
                Note: Requires API level 26.
             */
            inputTxt.setText(Base64.getEncoder().encodeToString(encryptedByteArr))
            //println(inputTxt.text.length)

            encBtn.isEnabled = false
            decBtn.isEnabled = true
        }

        decBtn.setOnClickListener {

            /*
                Initializes this cipher with the private key.
                First Param: operation mode = Decrypt.
                Second Param: Private key.
             */
            encryptCipher.init(Cipher.DECRYPT_MODE, keys.privateKey)

            /*
                Transforms the Base64 text (of encrypted bytes) to Bytes and decryps them
                Note: Requires API level 26 for Base64 decoder.
             */
            val decrByteArray = encryptCipher.doFinal(Base64.getDecoder().decode(inputTxt.text.toString()))

            inputTxt.setText(String(decrByteArray, charset))

            encBtn.isEnabled = true
            decBtn.isEnabled = false
        }

    }

}