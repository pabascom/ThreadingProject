package phil.homework.threadingproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_encryption.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.manager.CipherManager
import phil.homework.threadingproject.manager.KeyStoreManager
import java.security.KeyPair

class EncryptionActivity : AppCompatActivity() {

    private val keyStoreManager = KeyStoreManager(this)
    private val cipherManager = CipherManager()
    lateinit var keyPair: KeyPair

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryption)

        keyPair = keyStoreManager.createKeyPair("Sssssecretsss")

        btnEncryptText.setOnClickListener{
            tvEncryptedText.text = cipherManager.encrypt(etPlainText.text.toString(), keyPair.public)
        }

        btnDecryptText.setOnClickListener {
            tvDecryptedText.text = cipherManager.decrypt(tvEncryptedText.text.toString(), keyPair.private)
        }
    }
}
