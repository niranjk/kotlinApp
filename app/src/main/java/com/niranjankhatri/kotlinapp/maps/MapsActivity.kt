package com.niranjankhatri.kotlinapp.maps

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private var marker : Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // request permission from user
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted){
                getLastLocation()
            }else {
                showPermissionRationale {
                    requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
        }
    }

    // zoom the map to a given location
    private fun updateMapLocation(location: LatLng){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 7f))
    }

    // add a marker at that location
    private fun addMarkerAtLocation(location: LatLng, title: String,
                                    markerIcon: BitmapDescriptor? = null
    )= mMap.addMarker(MarkerOptions().title(title).position(location)).apply {
            markerIcon?.let { this?.setIcon(markerIcon) }
        }


    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        Log.d(TAG, "getCurrentLocation() called.")
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location : Location?  ->
            location?.let {
                val userLocation = LatLng(location.latitude, location.latitude)
                updateMapLocation(userLocation)
                addMarkerAtLocation(userLocation, "Niran")
            }
        }
    }

    private fun showPermissionRationale(positiveAction: () -> Unit){
        AlertDialog.Builder(this).setTitle("Location Permission")
            .setMessage("Location Permission is strictly required by this app to work.")
            .setPositiveButton(android.R.string.ok){_, _ -> positiveAction()}
            .setNegativeButton(android.R.string.cancel){dialog,_ -> dialog.dismiss()}
            .create().show()
    }

    private fun userHasLocationPermission() = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

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
        mMap = googleMap.apply {
            setOnMapClickListener { latLng ->
                addOrMoveSelectedPositionMarker(latLng)
            }
        }

        /**
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        */
        when {
            // Case 1 : permission granted
            userHasLocationPermission() -> {
                getLastLocation()
            }
            // Case 2:
            shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION) -> {
                showPermissionRationale {
                    requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
            // Case else
            else -> {
                requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun getBitmapDescriptorFromVector(
        @DrawableRes vectorDrawableResId: Int
    ): BitmapDescriptor? {
        val bitmap = ContextCompat.getDrawable(this,
        vectorDrawableResId)?.let { vectorDrawable ->
            vectorDrawable.setBounds(0,0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
            val drawableWithTint = DrawableCompat.wrap(vectorDrawable)
            DrawableCompat.setTint(drawableWithTint, Color.BLUE)
            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawableWithTint.draw(canvas)
            bitmap
        }
        return bitmap?.let {
            BitmapDescriptorFactory.fromBitmap(it).also {
                bitmap.recycle()
            }
        }
    }

    private fun addOrMoveSelectedPositionMarker(latLng: LatLng){
        if (marker == null){
            marker = addMarkerAtLocation(latLng, "Travel Here", getBitmapDescriptorFromVector(R.drawable.ic_travel))
        } else {
            marker?.apply {
                position = latLng
            }
        }
    }
    companion object {
        const val TAG = "MapsActivity"
    }
}