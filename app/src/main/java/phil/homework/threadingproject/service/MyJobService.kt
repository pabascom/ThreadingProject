package phil.homework.threadingproject.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.util.Log

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(MyJobService::class.java.simpleName, "onStartJob: ")
        startService(Intent(applicationContext, MyNormalService::class.java))
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(MyJobService::class.java.simpleName, "onStopJob: ")
        return false
    }
}
