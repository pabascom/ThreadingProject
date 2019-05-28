package phil.homework.threadingproject.manager

import android.util.Base64
import java.security.Key
import javax.crypto.Cipher

const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"

class CipherManager {

    val cipher = Cipher.getInstance(TRANSFORMATION)

    fun encrypt(plainText: String, key: Key): String{
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    fun decrypt(cipherText: String, key: Key): String{
        cipher.init(Cipher.DECRYPT_MODE, key)
        val encryptedBytes = Base64.decode(cipherText, Base64.DEFAULT)
        return String(cipher.doFinal(encryptedBytes))
    }

}