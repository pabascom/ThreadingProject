package phil.homework.threadingproject.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_static.*

import phil.homework.threadingproject.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class StaticFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(StaticFragment::class.java.simpleName, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(StaticFragment::class.java.simpleName, "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(StaticFragment::class.java.simpleName, "onCreateView: ")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_static, container, false)
    }
    
    fun updateTextView(string: String){
        tvStaticFragment.text = string
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(StaticFragment::class.java.simpleName, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(StaticFragment::class.java.simpleName, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(StaticFragment::class.java.simpleName, "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.d(StaticFragment::class.java.simpleName, "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(StaticFragment::class.java.simpleName, "onStop: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(StaticFragment::class.java.simpleName, "onDestroy: ")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(StaticFragment::class.java.simpleName, "onDetach: ")
        super.onDetach()
    }

    


}
