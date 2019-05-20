package phil.homework.receiverapplication

import android.Manifest
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_receiver.*

class ReceiverActivity : AppCompatActivity() {

    lateinit var myDynamicReceiver: MyDynamicReceiver
    lateinit var myIntentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)
        myDynamicReceiver = MyDynamicReceiver(tvReceiverContent)
        myIntentFilter = IntentFilter()
    }

    override fun onStart() {
        super.onStart()
        myIntentFilter.addAction(ACTION_CUSTOM_OUTSIDE)
        registerReceiver(myDynamicReceiver, myIntentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myDynamicReceiver)
    }
}
