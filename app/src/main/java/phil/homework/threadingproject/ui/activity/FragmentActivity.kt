package phil.homework.threadingproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_fragment.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.ui.fragment.DynamicFragment
import phil.homework.threadingproject.ui.fragment.HeadlessFragment
import phil.homework.threadingproject.ui.fragment.StaticFragment
import phil.homework.threadingproject.util.toast

class FragmentActivity : AppCompatActivity(), DynamicFragment.OnFragmentInteractionListener {

    lateinit var headlessFragment: HeadlessFragment

    override fun onFragmentInteraction(string: String) {
        val staticFragment = supportFragmentManager.findFragmentByTag("staticFragment") as StaticFragment
        staticFragment.updateTextView(string)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d(FragmentActivity::class.java.simpleName, "onCreate: ")

        staticFragment.retainInstance = true

        val dynamicFragment = DynamicFragment.newInstance("param1", "param2")


        btnAddFragment.setOnClickListener {
            headlessFragment.value = "New Value"
            supportFragmentManager.beginTransaction()
                .add(R.id.dynamicFragment, dynamicFragment, "dynamicFragment")
                .add(R.id.dynamicFragment2, StaticFragment(), "staticFragment")
                .addToBackStack("addFragments")
                .commit()
        }
        btnRemoveFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .remove(dynamicFragment)
                .addToBackStack("removeFragment")
                .commit()
            supportFragmentManager.popBackStack("addFragment", androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
            toast(headlessFragment.value)
        }
    }

    override fun onStart() {
        super.onStart()

        val fragment = supportFragmentManager.findFragmentByTag("headless")

        if(fragment!=null){
            headlessFragment = fragment as HeadlessFragment
            headlessFragment.retainInstance = true
        } else {
            headlessFragment = HeadlessFragment()
            supportFragmentManager.beginTransaction()
                .add(headlessFragment, "headless")
                .commit()
        }

        Log.d(FragmentActivity::class.java.simpleName, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(FragmentActivity::class.java.simpleName, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(FragmentActivity::class.java.simpleName, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(FragmentActivity::class.java.simpleName, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(FragmentActivity::class.java.simpleName, "onDestroy: ")
    }
}
