package phil.homework.threadingproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_analytics.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.manager.AnalyticsManager
import java.lang.RuntimeException

class AnalyticsActivity : AppCompatActivity(), View.OnClickListener {
    private val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Puppy_dog_face.jpg/1200px-Puppy_dog_face.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        btnForceCrash.setOnClickListener { throw(RuntimeException("Oh nooooo!")) }

    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.btnRunning -> AnalyticsManager.logEvent("Running", "1", "Click")
            R.id.btnWalking -> AnalyticsManager.logEvent("Walking", "2", "Click")
            R.id.btnSwimming -> AnalyticsManager.logEvent("Swimming", "3", "Click")
            R.id.btnLoadImage -> loadImage()

        }
    }

    private fun loadImage() {
        Glide.with(this).load(imageUrl).into(ivMain)
    }
}
