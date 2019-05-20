package phil.homework.threadingproject.ui.activity.firebase

import phil.homework.threadingproject.manager.LoginManager
import javax.security.auth.callback.Callback

class FirebasePresenter : FirebaseContract.Presenter {

    private var view: FirebaseContract.View? = null

    override fun createUser(email: String, password: String) {
        LoginManager.createUser(email, password) {
            view?.onUserCreation(it)
        }
    }

    override fun signIn(email: String, password: String) {
        LoginManager.signIn(email, password) {
            view?.onLoginResults(it)
        }
    }

    override fun signOut() {
        LoginManager.signOut { view?.onSignOut(it) }
    }

    override fun checkSession(callback: (Boolean) -> Unit) {
        callback.invoke(LoginManager.userIsSignedIn())
    }

    override fun readDataFromCloud() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveDataToCloud(data: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addView(view: FirebaseContract.View) {
        this.view = view
    }

    override fun removeView() {
        this.view = null
    }

}