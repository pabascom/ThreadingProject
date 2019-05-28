package phil.homework.threadingproject.ui.activity

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_animation.*
import phil.homework.threadingproject.R

class AnimationActivity : AppCompatActivity() {

    var translationX = 0f
    var metric = 0f
    var translationZ = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        btnAnimate.setOnClickListener { animate(tvMain) }
    }

    private fun animate(view: TextView){

        val valueAnimator = ValueAnimator.ofFloat(metric, metric + 500f)
            .apply{
                interpolator = AccelerateDecelerateInterpolator()
                duration = 5000
                addUpdateListener { animation ->
                    metric = animation.animatedValue as Float
                    val relativeMetric = metric/500f
                    view.translationY = metric
                    view.rotation = 360*(metric/500f)
                }
                //starts the animation
                start()
            }

    }
}
