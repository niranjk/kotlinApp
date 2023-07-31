package com.niranjankhatri.kotlinapp.workmanager

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.*
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.workmanager.PlayerRouteTrackingService.Companion.EXTRA_PLAYER_ID

/****
 * ****************** Services, WorkManager and Notifications *********************
 */
class WorkManagerActivity : AppCompatActivity() {
    private val workManager = WorkManager.getInstance(this)

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        // Show Notification Permission
        ensuerPermissionGrantedAndDispatchPlayer()
          }

    private fun ensuerPermissionGrantedAndDispatchPlayer(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            dispatchPlayer()
            return
        }

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted){
                dispatchPlayer()
            }
            else {
                showPermissionRationale {
                    requestPermissionLauncher.launch(POST_NOTIFICATIONS)
                }
            }
        }

        when {
            checkSelfPermission(POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED -> {
                dispatchPlayer()
            }
            shouldShowRequestPermissionRationale(POST_NOTIFICATIONS) -> {
                showPermissionRationale {
                    requestPermissionLauncher.launch(POST_NOTIFICATIONS)
                }
            }
            else -> requestPermissionLauncher.launch(POST_NOTIFICATIONS)
        }
    }

    private fun showPermissionRationale(positiveAction:() -> Unit){
        AlertDialog.Builder(this)
            .setTitle("Notifications Permissions")
            .setMessage("To show progress, we need the notification permission")
            .setPositiveButton(android.R.string.ok){_,_ -> positiveAction()}
            .setNegativeButton(android.R.string.cancel){dialog,_ -> dialog.dismiss()}
            .create().show()
    }

    private fun dispatchPlayer(){
        val networkConstraints = Constraints.Builder().setRequiredNetworkType(
            NetworkType.CONNECTED
        ).build()

        val playerId = "Player1"
        val playerStretchingRequest = OneTimeWorkRequest.Builder(PlayerStretchingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getPlayerIdInputData(PlayerStretchingWorker.INPUT_DATA_PLAYER_ID, playerId)
            ).build()

        val playerPlayingRequest = OneTimeWorkRequest.Builder(PlayerPlayWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getPlayerIdInputData(PlayerPlayWorker.INPUT_DATA_PLAYER_ID, playerId)
            ).build()

        val playerRestRequest = OneTimeWorkRequest.Builder(PlayerRestWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(
                getPlayerIdInputData(PlayerRestWorker.INPUT_DATA_PLAYER_ID, playerId)
            ).build()

        workManager.getWorkInfoByIdLiveData(playerStretchingRequest.id)
            .observe(this){ info ->
                if (info.state.isFinished){
                    showResult("Player done stretching.")
                }
            }

        workManager.getWorkInfoByIdLiveData(playerPlayingRequest.id)
            .observe(this){ info ->
                if (info.state.isFinished){
                    showResult("Player done playing.")
                }
            }

        workManager.getWorkInfoByIdLiveData(playerRestRequest.id)
            .observe(this){ info ->
                if (info.state.isFinished){
                    showResult("Player done resting.")
                    launchTrackingService()
                }
            }

        workManager.beginWith(playerStretchingRequest).then(playerPlayingRequest).then(playerRestRequest).enqueue()

    }

    private fun showResult(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getPlayerIdInputData(
        playerIdKey: String,
        payerIdValue: String
    ) = Data.Builder().putString(
        playerIdKey,
        payerIdValue
    ).build()

    private fun launchTrackingService(){
        PlayerRouteTrackingService.trackingCompletion.observe(this){ playerId ->
            showResult("Player $playerId arrived in the playground!")
        }
        val serviceIntent = Intent(this, PlayerRouteTrackingService::class.java).apply {
            putExtra(EXTRA_PLAYER_ID, "009")
        }
        ContextCompat.startForegroundService(this, serviceIntent)
    }
}