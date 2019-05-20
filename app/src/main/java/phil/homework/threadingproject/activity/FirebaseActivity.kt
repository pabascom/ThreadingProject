package phil.homework.threadingproject.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_firebase.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.manager.CloudManager
import phil.homework.threadingproject.manager.LoginManager

class FirebaseActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        btnCreateUser.setOnClickListener {
            LoginManager.createUser(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }

        btnSignIn.setOnClickListener {
            LoginManager.signIn(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }

        btnSignOut.setOnClickListener {
            LoginManager.signOut()
        }

        btnFirebaseSave.setOnClickListener {
            CloudManager.saveFirebaseData(etSimpleData.text.toString())
        }

        btnFirebaseRead.setOnClickListener {
            CloudManager.readFirebaseData()
        }
    }

    override fun onStart() {
        super.onStart()
        if(LoginManager.userIsSignedIn()){
            tvSignInStatus.text = "Signed In!"
        } else {
            tvSignInStatus.text = "Signed Out :("
        }
    }

}
