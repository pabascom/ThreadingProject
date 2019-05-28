package phil.homework.threadingproject.manager

import android.content.Context
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.util.*
import javax.security.auth.x500.X500Principal

const val KEYSTORE_PROVIDER = "AndroidKeyStore"
const val KEY_PAIR_GENERATOR_ALGORITHM = "RSA"

class KeyStoreManager(val context: Context) {

    val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
    val keyPairGenerator = KeyPairGenerator.getInstance(KEY_PAIR_GENERATOR_ALGORITHM, KEYSTORE_PROVIDER)

    fun createKeyPair(alias: String): KeyPair {

        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val spec = KeyPairGeneratorSpec.Builder(context)
            .setAlias(alias)
            .setSerialNumber(BigInteger.ONE) // for the example
            .setSubject(X500Principal("CN={alias} CA Certificate")) // normally this would be stored somewhere on the device
            .setStartDate(startDate.time)
            .setEndDate(endDate.time)
            .build()

            //val spec2 = KeyGenParameterSpec.Builder(context)

        keyPairGenerator.initialize(spec)
        return keyPairGenerator.generateKeyPair()
    }

    fun getKeyPair(alias: String): KeyPair {
        val privateKey = keyStore.getKey(alias, null) as PrivateKey
        val publicKey = keyStore.getCertificate(alias).publicKey
        return KeyPair(publicKey, privateKey)
    }

    fun removeKeyPair(alias: String) = keyStore.deleteEntry(alias)

}