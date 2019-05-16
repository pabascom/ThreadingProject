package phil.homework.receiverapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView

const val ACTION_CUSTOM_OUTSIDE = "myapplication.action.outside"
const val KEY_OUTSIDE = "outside_key"
class MyDynamicReceiver(val tvReceiverContent: TextView) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(MyDynamicReceiver::class.java.simpleName, "onReceive: DynamicReceiver!")

        tvReceiverContent.text = intent.getStringExtra(KEY_OUTSIDE)
    }
}
