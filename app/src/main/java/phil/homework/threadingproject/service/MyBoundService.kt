package phil.homework.threadingproject.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.lang.StringBuilder

class MyBoundService : Service() {

    var dataList=  mutableListOf<String>()

    val myBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d(MyBoundService::class.java.simpleName, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(MyBoundService::class.java.simpleName, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(MyBoundService::class.java.simpleName, "onDestroy: ")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(MyBoundService::class.java.simpleName, "onBind: ")
        return myBinder
    }

    fun addToList(string: String) = dataList.add(string)

    fun clearList() = dataList.clear()

    inner class MyBinder: Binder(){
        fun getService(): MyBoundService {
            return this@MyBoundService
        }
    }
}