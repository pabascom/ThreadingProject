package phil.homework.threadingproject.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyNormalService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(MyNormalService::class.java.simpleName, "onCreate: MyNormalService, Thread: ${Thread.currentThread().name}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(MyNormalService::class.java.simpleName, "onStartCommand: ${intent?.getStringExtra("key")}, Thread: ${Thread.currentThread().name}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(MyNormalService::class.java.simpleName, "onDestroy: MyNormalService, Thread: ${Thread.currentThread().name}")
        super.onDestroy()
    }



    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
