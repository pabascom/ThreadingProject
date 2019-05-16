package phil.homework.threadingproject.thread

import android.os.Handler
import android.os.Message
import phil.homework.threadingproject.manager.TaskManager

class MyRunnable(val handler: Handler, val sequence: Int) : Runnable {

    override fun run() {
        TaskManager.startTask("MyRunnable", sequence) {sendMessage(it)}
    }

    fun sendMessage(int: Int) {
        handler.sendMessage(Message().apply{data.putInt("value", int)})
    }
}