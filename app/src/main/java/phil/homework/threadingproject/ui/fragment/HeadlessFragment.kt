package phil.homework.threadingproject.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment

class HeadlessFragment: Fragment() {
    var value: String = "default value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}