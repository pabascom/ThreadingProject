package phil.homework.threadingproject

import android.app.Application
import android.content.Context
import phil.homework.threadingproject.di.DaggerFirebaseComponent
import phil.homework.threadingproject.di.FirebaseComponent
import phil.homework.threadingproject.di.FirebaseModule
import phil.homework.threadingproject.manager.CloudManager
import phil.homework.threadingproject.manager.LoginManager

class ThreadingApp: Application() {

    val firebaseComponent by lazy {
        DaggerFirebaseComponent.builder()
            .firebaseModule(FirebaseModule(applicationContext))
            .build()
    }

    companion object {
        fun get(context: Context): ThreadingApp = context.applicationContext as ThreadingApp
    }

    override fun onCreate() {
        super.onCreate()
        CloudManager.context = applicationContext
    }
}