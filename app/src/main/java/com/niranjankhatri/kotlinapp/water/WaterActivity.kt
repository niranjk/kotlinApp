package com.niranjankhatri.kotlinapp.water

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.water.WaterTrackingService.Companion.EXTRA_INTAKE_AMOUNT_MILLS

class WaterActivity : AppCompatActivity() {
    private lateinit var requestPemissionLauncher : ActivityResultLauncher<String>

    private val waterButton: View by lazy { findViewById(R.id.main_water_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water)

        waterButton.setOnClickListener {
            launchTrackingService(250f)
        }
        waterButton.isEnabled = false
        // ask for user consent
        ensurePermissionGrantedAndLauchTracking()
    }

    private fun ensurePermissionGrantedAndLauchTracking(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            launchTrackingService()
            return
        }

        requestPemissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted){
                launchTrackingService()
            }
            else {
                showPermissionRationale {
                    requestPemissionLauncher.launch(POST_NOTIFICATIONS)
                }
            }
        }

        when{
            checkSelfPermission(POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED -> {
                launchTrackingService()
            }
            shouldShowRequestPermissionRationale(POST_NOTIFICATIONS) -> {
                showPermissionRationale {
                    requestPemissionLauncher.launch(POST_NOTIFICATIONS)
                }
            }
            else -> requestPemissionLauncher.launch(POST_NOTIFICATIONS)
        }
    }

    private fun showPermissionRationale(positiveAction: () -> Unit){
        AlertDialog.Builder(this)
            .setTitle("Notification Permission")
            .setMessage("To show our current fluid balanc, we nee the notifications permission")
            .setPositiveButton(android.R.string.ok){_,_ -> positiveAction()}
            .setNegativeButton(android.R.string.cancel){dialog, _ -> dialog.dismiss()}
            .create().show()
    }

    private fun launchTrackingService(intakeAmount: Float = 0f){
        waterButton.isEnabled = true
        val serviceIntent = Intent(this, WaterTrackingService::class.java).apply {
            putExtra(EXTRA_INTAKE_AMOUNT_MILLS, intakeAmount)
        }
        ContextCompat.startForegroundService(this, serviceIntent)
    }
}