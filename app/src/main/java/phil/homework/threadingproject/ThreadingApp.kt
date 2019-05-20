package phil.homework.threadingproject

import android.app.Application
import phil.homework.threadingproject.manager.CloudManager
import phil.homework.threadingproject.manager.LoginManager

class ThreadingApp: Application() {

    override fun onCreate() {
        super.onCreate()
        LoginManager.context = applicationContext
        CloudManager.context = applicationContext
    }
}