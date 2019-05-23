package phil.homework.threadingproject.manager

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionManager {
    enum class PERMISSION { CONTACT, LOCATION }

    const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10
    const val MY_PERMISSIONS_REQUEST_LOCATION = 20
    val CONTACT_PERMISSIONS = arrayOf(Manifest.permission.READ_CONTACTS)
    val LOCATION_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun checkPermission(activity: Activity, permission: PERMISSION, callback: (Boolean) -> Unit) {
        when (permission) {
            PermissionManager.PERMISSION.CONTACT -> checkCurrentPermission(
                CONTACT_PERMISSIONS, MY_PERMISSIONS_REQUEST_READ_CONTACTS, activity, callback
            )
            PermissionManager.PERMISSION.LOCATION -> checkCurrentPermission(
                LOCATION_PERMISSIONS, MY_PERMISSIONS_REQUEST_LOCATION, activity, callback
            )
        }

    }

    fun checkCurrentPermission(
        permissions: Array<String>,
        PERMISSION_CODE: Int,
        thisActivity: Activity,
        callback: (Boolean) -> Unit
    ) {

        permissions.forEach {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(
                    thisActivity,
                    it
                )
                != PackageManager.PERMISSION_GRANTED
            ) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        thisActivity,
                        it
                    )
                ) {
                    // Show an explanation to the user asynchronously -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(
                        thisActivity,
                        arrayOf(it),
                        PERMISSION_CODE
                    )

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                callback.invoke(true)
            }
        }


    }

    fun onPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray, callback: (Boolean) -> Unit
    ) {

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    callback.invoke(true)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    callback.invoke(false)
                }
            }
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    callback.invoke(true)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    callback.invoke(false)
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

}