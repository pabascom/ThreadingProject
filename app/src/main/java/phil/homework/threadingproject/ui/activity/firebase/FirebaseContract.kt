package phil.homework.threadingproject.ui.activity.firebase

import phil.homework.threadingproject.ui.base.BasePresenter
import phil.homework.threadingproject.ui.base.BaseView

interface FirebaseContract {

    interface View: BaseView {

        fun onUserCreation(isCreated: Boolean)
        fun onLoginResults(isValid: Boolean)
        fun onSignOut(signedOut: Boolean)

    }

    interface Presenter: BasePresenter<View> {

        // Auth
        fun createUser(email: String, password: String)
        fun signIn(email: String, password: String)
        fun signOut()
        fun checkSession(callback: (Boolean) -> Unit)

        // Real-time database
        fun readDataFromCloud()
        fun saveDataToCloud(data: String)
    }



}