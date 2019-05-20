package phil.homework.threadingproject.manager

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
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

    fun createUser(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                taskResults(it, callback)
            }
    }

    fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                taskResults(it, callback)
            }
    }

    fun signOut(callback: (Boolean) -> Unit) {
        auth.signOut()
        callback.invoke(true)
    }

    var taskResults: (Task<AuthResult>, (Boolean) -> Unit ) -> Unit = { task, callback ->
        if(task.isSuccessful) callback.invoke(true)
        else callback.invoke(false)
    }
}