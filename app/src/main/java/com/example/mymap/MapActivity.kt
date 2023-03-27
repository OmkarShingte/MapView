package com.example.mymap
import android.content.res.Configuration
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val email = intent.getStringExtra("email")

        emailTextView.text = email

        mapView = MapView(this)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val mapContainer = findViewById<FrameLayout>(R.id.map_container)
        mapContainer.addView(mapView)

    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val center = LatLng(37.4219999,-122.0840575)
        mMap.addMarker(MarkerOptions().position(center))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 15f))
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//
//            // Inflate the appropriate layout file based on orientation
//            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                setContentView(R.layout.map_screen_land)
//            } else {
//                setContentView(R.layout.activity_map)
//            }
//
//            mapView = findViewById(R.id.mapView)
//            titleTextView = findViewById(R.id.titleTextView)
//            titleTextView.text = email
//            mapView.onCreate(null)
//            mapView.onResume()
//
//    }
}
