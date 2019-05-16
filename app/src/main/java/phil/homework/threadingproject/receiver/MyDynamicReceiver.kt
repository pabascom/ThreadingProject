package phil.homework.threadingproject.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import phil.homework.threadingproject.util.KEY_CUSTOM

class MyDynamicReceiver(val tvReceiverContent: TextView) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(MyDynamicReceiver::class.java.simpleName, "onReceive: DynamicReceiver!")

        tvReceiverContent.text = intent.getStringExtra(KEY_CUSTOM)
    }
}
