package phil.homework.threadingproject.manager

import android.util.Log

object TaskManager {

    fun startTask(className: String, sequence: Int, callback: (Int) -> Unit){
        (1..sequence).forEach{
            Thread.sleep(500)
            Log.d("ThreadingActivity", "$className task $it running on ${Thread.currentThread().name}")
            callback.invoke(it)
        }
    }

}