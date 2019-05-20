package phil.homework.threadingproject.manager

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import phil.homework.threadingproject.util.toast

@SuppressLint("StaticFieldLeak")
object LoginManager {

    lateinit var context: Context

    var auth = FirebaseAuth.getInstance()

    fun userIsSignedIn(): Boolean {
        return auth.currentUser != null
    }

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    context.toast("User created")
                } else {
                    context.toast("User not created")
                }
            }
    }

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    context.toast("User signed in")
                } else {
                    context.toast("User not signed in")
                }
            }
    }

    fun signOut() {
        auth.signOut()
        context.toast("User signed out")
    }
}