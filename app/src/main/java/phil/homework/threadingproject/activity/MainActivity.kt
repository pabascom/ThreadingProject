package phil.homework.threadingproject.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import phil.homework.threadingproject.R
import phil.homework.threadingproject.data.MessageEvent
import phil.homework.threadingproject.thread.MyAsyncTask
import phil.homework.threadingproject.thread.MyRunnable
import phil.homework.threadingproject.thread.MyThread

class MainActivity : AppCompatActivity(), View.OnClickListener, Handler.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(MainActivity::class.java.simpleName, "onCreate")

        tvResult.text = "It works!"
        btnThread.setOnClickListener(this)
        btnRunnable.setOnClickListener(this)
        btnAsyncTask.setOnClickListener {
            Thread.sleep(10000)
            MyAsyncTask { tvResult.text = it.toString() }.execute(10)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnThread -> MyThread(applicationContext, 10).start()
            R.id.btnRunnable -> {
                Thread(MyRunnable(Handler(this), 10)).start()
            }
            R.id.btnAsyncTask -> {

            }
        }
    }

    override fun handleMessage(msg: Message?): Boolean {
        Toast.makeText(applicationContext, msg?.data?.getInt("value").toString(), Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(message: MessageEvent) {
        Log.d(
            MainActivity::class.java.simpleName, "onMessageEvent: ${message.data}" +
                    " on thread ${Thread.currentThread().name}"
        )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEventAgain(message: MessageEvent) {
        Log.d(
            MainActivity::class.java.simpleName, "onMessageEventAgain: ${message.data}" +
                    " on thread ${Thread.currentThread().name}"
        )
    }
}
