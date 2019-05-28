package phil.homework.threadingproject.manager

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import android.util.StatsLog.logEvent
import android.R.id
import android.os.Bundle

const val CLICK_ID = 1

object AnalyticsManager {

    lateinit var firebaseAnalytics: FirebaseAnalytics

    // Application Context
    fun init(context: Context){
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun logEvent(eventName: String, eventId: String, contentType: String){

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, eventId)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, eventName)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}