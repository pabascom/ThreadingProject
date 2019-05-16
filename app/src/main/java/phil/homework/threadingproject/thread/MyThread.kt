package phil.homework.threadingproject.thread

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import phil.homework.threadingproject.data.MessageEvent
import phil.homework.threadingproject.manager.TaskManager

class MyThread(val context: Context, val sequence: Int): Thread() {

    val handler: Handler = Handler(Looper.getMainLooper())

    override fun run() {
        TaskManager.startTask("MyThread", sequence){sendMessage(it)}
        EventBus.getDefault().post(MessageEvent("Task Completed"))
        super.run()
    }

    fun sendMessage(int: Int){
        handler.post { Toast.makeText(context,"Tick: $int", Toast.LENGTH_SHORT).show() }
    }



}