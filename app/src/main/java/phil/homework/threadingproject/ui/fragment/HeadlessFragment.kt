package phil.homework.threadingproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

class HeadlessFragment: androidx.fragment.app.Fragment() {
    var value: String = "default value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}