package com.niranjankhatri.kotlinapp.parking

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.View
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
import com.niranjankhatri.kotlinapp.databinding.ActivityParkingBinding

class ParkingActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityParkingBinding

    private lateinit var requestPermissionLauncher : ActivityResultLauncher<String>
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private var userMarker: Marker? = null
    private var carMarker: Marker? = null

    private val markLocationButton: View by lazy {
        findViewById(R.id.maps_mark_location_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParkingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // ask for user's permission for location
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted){
                // get the last location of user
                getLastLocation(::updateMapLocationWithMarker)
            }
            else {
                showPermissionRationale {
                    requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
        }

        markLocationButton.setOnClickListener {
            if (hasLocationPermission()){
                markParkedCar()
            }
        }
    }

    private fun markParkedCar(){
        getLastLocation { location ->
            val userLocation = updateMapLocationWithMarker(location)
            carMarker?.remove()
            carMarker = addMarkerAtLocation(
                userLocation,
                "Your Car",
                getBitmapDescriptorFromVector(R.drawable.ic_electric_car)
            )
            saveLocation(userLocation)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(onLocation: (location: Location) -> Unit){
       fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
           location?.let {
               onLocation(it)
           }
       }
    }

    private fun addMarkerAtLocation(
        latLng: LatLng,
        title: String,
        markerIcon: BitmapDescriptor? = null
    ) = mMap.addMarker(
        MarkerOptions().title(title).position(latLng).apply {
            markerIcon?.let { icon(markerIcon) }
        }
    )
    private fun updateMapLocation(latLng: LatLng){
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7f))
    }

    private fun showPermissionRationale(positiveAction: ()-> Unit){
        AlertDialog.Builder(this)
            .setTitle("Location Permission")
            .setMessage("We need your location permission to run this app.")
            .setPositiveButton(android.R.string.ok){_,_ -> positiveAction()}
            .setNegativeButton(android.R.string.cancel){ dialog, _ -> dialog.dismiss()}
            .create().show()
    }

    private fun hasLocationPermission() = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED

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
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        restoreLocation()?.let { userLocation ->
            carMarker = addMarkerAtLocation(
                userLocation,
                "Your Car",
                getBitmapDescriptorFromVector(R.drawable.ic_electric_car)
            )
            userMarker = addMarkerAtLocation(userLocation, "You")
        }
        // ask for permission
        when{
            // CASE: 1 : have location permission
            hasLocationPermission() -> getLastLocation(::updateMapLocationWithMarker)
            // CASE 2 : check if we should show request permission rationale
            shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION) -> {
                showPermissionRationale {
                    requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
            else ->  requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }

    private fun updateMapLocationWithMarker(location: Location): LatLng {
        val userLocation = LatLng(location.latitude, location.longitude)
        updateMapLocation(userLocation)
        userMarker?.remove()
        userMarker = addMarkerAtLocation(userLocation, "You")
        return userLocation
    }

    private fun getBitmapDescriptorFromVector(
        @DrawableRes vectorDrawableResId: Int
    ): BitmapDescriptor? {
        val bitmap = ContextCompat.getDrawable(this, vectorDrawableResId)?.let { vectorDrawable ->
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
        } ?: return null
        return BitmapDescriptorFactory.fromBitmap(bitmap).also {
            bitmap.recycle()
        }
    }

    private fun saveLocation(latLng: LatLng)=
        getPreferences(MODE_PRIVATE)?.edit()?.apply {
            putString("latitude", latLng.latitude.toString())
            putString("longitude", latLng.longitude.toString())
            apply()
        }

    private fun restoreLocation() =
        getPreferences(Context.MODE_PRIVATE)?.let { sharedPreferences ->
            val latitude = sharedPreferences.getString("latitude",null)?.toDoubleOrNull() ?: return null
            val longitude = sharedPreferences.getString("longitude", null)?.toDoubleOrNull() ?: return null
            LatLng(latitude, longitude)
        }
}