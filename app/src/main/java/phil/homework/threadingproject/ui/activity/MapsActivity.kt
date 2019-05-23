package phil.homework.threadingproject.ui.activity

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.manager.LocationManager
import phil.homework.threadingproject.manager.PermissionManager
import phil.homework.threadingproject.util.toast

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnStartLocationUpdates.setOnClickListener {
            LocationManager.startListening(this) {
                toast("Lat: ${location.latitude}, Long: ${location.longitude}")
            }
        }

        btnStopLocationUpdates.setOnClickListener {
            LocationManager.stopListening()
        }
    }

    override fun onStart() {
        super.onStart()
        PermissionManager.checkPermission(this, PermissionManager.PERMISSION.LOCATION) { isGranted ->
            if (isGranted) {
                LocationManager.getLastKnownLocation(this) {
                    location = it
                    updateMap(LatLng(location.latitude, location.longitude))
                }

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.onPermissionResult(requestCode, permissions, grantResults) {}
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        updateMap(sydney)
    }

    private fun updateMap(location: LatLng) {
        mMap.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}
