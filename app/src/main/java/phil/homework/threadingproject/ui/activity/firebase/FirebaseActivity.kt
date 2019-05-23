package phil.homework.threadingproject.ui.activity.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_firebase.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.ThreadingApp
import phil.homework.threadingproject.di.DaggerFirebaseComponent
import phil.homework.threadingproject.di.FirebaseModule
import phil.homework.threadingproject.manager.CloudManager
import phil.homework.threadingproject.manager.LoginManager
import phil.homework.threadingproject.util.toast
import javax.inject.Inject

class FirebaseActivity : AppCompatActivity(), FirebaseContract.View {

    @Inject
    lateinit var presenter: FirebasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        //presenter = FirebasePresenter(LoginManager(applicationContext))

        //Create user
        btnCreateUser.setOnClickListener {
            presenter.createUser(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }

        //Sign in user
        btnSignIn.setOnClickListener {
            presenter.signIn(
                etEmail.text.toString(),
                etPassword.text.toString()
            )
        }

        //Sign out user
        btnSignOut.setOnClickListener {
            presenter.signOut()
        }

        //Save and load data
        btnFirebaseSave.setOnClickListener {
            CloudManager.saveFirebaseData(etSimpleData.text.toString())
        }

        btnFirebaseRead.setOnClickListener {
            CloudManager.readFirebaseData()
        }
    }

    override fun onStart() {
        super.onStart()
        ThreadingApp.get(this).firebaseComponent.inject(this)
        presenter.addView(this)
        presenter.checkSession { isSignedIn ->
            if(isSignedIn) tvSignInStatus.text = "Signed in!"
            else tvSignInStatus.text = "Signed out :("
        }
    }

    override fun onStop() {
        // ThreadingApp.get(this).clearComponent(FirebaseComponent)
        super.onStop()
        presenter.removeView()
    }

    override fun onUserCreation(isCreated: Boolean) {
        toast("User Created: $isCreated")
    }

    override fun onLoginResults(isValid: Boolean) {
        toast("Login Results: $isValid")
    }

    override fun onSignOut(signedOut: Boolean) {
        toast("Signed out: $signedOut")
    }

    override fun showError(error: String) {
        toast("Error! $error")
    }

}
