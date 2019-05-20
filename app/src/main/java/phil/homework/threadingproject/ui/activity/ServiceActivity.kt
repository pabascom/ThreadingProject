package phil.homework.threadingproject.ui.activity

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_service.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.service.MyBoundService
import phil.homework.threadingproject.service.MyIntentService
import phil.homework.threadingproject.service.MyJobService
import phil.homework.threadingproject.service.MyNormalService
import phil.homework.threadingproject.util.toast

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ServiceActivity : AppCompatActivity(), ServiceConnection {

    lateinit var myBoundService: MyBoundService
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val intent = Intent(applicationContext, MyNormalService::class.java).putExtra("key", "some data")
        btnStartNormal.setOnClickListener { startService(intent) }
        btnStopNormal.setOnClickListener { stopService(intent) }

        btnStartFoo.setOnClickListener { MyIntentService.startActionFoo(applicationContext, "Foo1", "Foo2") }
        btnStartBaz.setOnClickListener { MyIntentService.startActionBaz(applicationContext, "Baz1", "Baz2") }

        // Bind Service
        val bindIntent = Intent(applicationContext, MyBoundService::class.java)
        btnBindService.setOnClickListener { bindService(bindIntent, this, Context.BIND_AUTO_CREATE) }

        btnAddToList.setOnClickListener { if (isBound) myBoundService.addToList(etAddToList.text.toString()) }
        btnClearList.setOnClickListener { if (isBound) myBoundService.clearList() }
        btnPrintList.setOnClickListener { if (isBound) myBoundService.dataList.forEach { toast(it) } }
        btnUnbind.setOnClickListener {
            if (isBound) {
                unbindService(this)
                isBound = false
            }
        }

        // Scheduling a job
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(applicationContext, MyJobService::class.java)
        val jobInfo = JobInfo.Builder(0, componentName)
            .setMinimumLatency(1000)
            .setRequiresCharging(true)
            .build()

        btnScheduleService.setOnClickListener { jobScheduler.schedule(jobInfo) }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MyBoundService.MyBinder
        myBoundService = binder.getService()
        isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isBound = false
    }

}
