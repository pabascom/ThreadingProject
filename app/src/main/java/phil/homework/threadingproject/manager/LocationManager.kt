package phil.homework.threadingproject.manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender
import android.location.Location
import android.util.Log
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task

object LocationManager {

    lateinit var client: SettingsClient
    lateinit var task: Task<LocationSettingsResponse>
    lateinit var fusedLocationClient: FusedLocationProviderClient

    val locationRequest = LocationRequest.create()?.apply {
        interval = 2000
        fastestInterval = 1000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val builder = locationRequest?.let {
        LocationSettingsRequest.Builder()
            .addLocationRequest(it)
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(activity: Activity, callback: (Location) -> Unit) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient.lastLocation.addOnSuccessListener { callback.invoke(it) }

    }

    @SuppressLint("MissingPermission")
    fun startListening(activity: Activity, callback: (Location) -> Unit) {
        client = LocationServices.getSettingsClient(activity)
        task = client.checkLocationSettings(builder?.build())
        task.addOnSuccessListener {
            Log.d(LocationManager::class.java.simpleName, "startListening: true")
            fusedLocationClient.requestLocationUpdates(locationRequest, getCallback(callback), null)
        }.addOnFailureListener {exception ->  
            if(exception is ResolvableApiException) {
                try{
                    Log.d(LocationManager::class.java.simpleName, "startListening: isResolvable")
                    exception.startResolutionForResult(activity, 10)
                } catch(sendEx: IntentSender.SendIntentException) {
                    Log.d(LocationManager::class.java.simpleName, "startListening: sendEx: $sendEx")
                }
            }
        }
    }

    private lateinit var locationCallback: LocationCallback
    private fun getCallback(callback: (Location) -> Unit): LocationCallback {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                locationResult?.locations?.forEach { callback.invoke(it) }
            }
        }
        return locationCallback
    }

    fun stopListening() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

}