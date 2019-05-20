package phil.homework.threadingproject.ui.activity

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_receiver.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.receiver.MyDynamicReceiver
import phil.homework.threadingproject.util.*

class ReceiverActivity : AppCompatActivity() {

    lateinit var myDynamicReceiver: MyDynamicReceiver
    lateinit var myIntentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)
        myDynamicReceiver = MyDynamicReceiver(tvReceiverContent)
        myIntentFilter = IntentFilter()

        btnSendBroadcast.setOnClickListener {
            sendBroadcast(
                Intent(ACTION_CUSTOM).putExtra(
                    KEY_CUSTOM,
                    etReceiverContent.text.toString()
                )
            )
        }

        btnSendBroadcastOutside.setOnClickListener {
            sendBroadcast(
                Intent(ACTION_CUSTOM_OUTSIDE).putExtra(
                    KEY_OUTSIDE,
                    etReceiverContent.text.toString()
                ),
                Manifest.permission.SEND_SMS
            )
        }
    }

    override fun onStart() {
        super.onStart()
        myIntentFilter.addAction(ACTION_CUSTOM)
        registerReceiver(myDynamicReceiver, myIntentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myDynamicReceiver)
    }


}
