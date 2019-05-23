package phil.homework.threadingproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_custom_view.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.ui.custom_views.ToastedButton

class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        
        toastedBtn.setOnToastClickListener(object: ToastedButton.OnToastClickListener{
            override fun onClick() {
                Log.d(CustomViewActivity::class.java.simpleName, "onClick: Stuff happened")
            }
        })
    }
}
