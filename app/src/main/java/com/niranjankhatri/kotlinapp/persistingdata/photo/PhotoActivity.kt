package com.niranjankhatri.kotlinapp.persistingdata.photo

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.niranjankhatri.kotlinapp.databinding.ActivityPhotoBinding
import java.util.concurrent.Executors

class PhotoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPhotoBinding

    companion object{
        private const val REQUEST_EXTERNAL_STORAGE = 3
    }
    private lateinit var providerFileManager: ProviderFileManager
    private var photoInfo: FileInfo? = null
    private var videoInfo: FileInfo? = null
    private var isCapturingVideo = false
    private lateinit var takePictureLauncher : ActivityResultLauncher<Uri>
    private lateinit var takeVideoLauncher : ActivityResultLauncher<Uri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        providerFileManager = ProviderFileManager(
            applicationContext,
            FileHelper(applicationContext),
            contentResolver,
            Executors.newSingleThreadExecutor(),
            MediaContentHelper()
        )

        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){
            providerFileManager.insertImageToStore(photoInfo)
        }

        takeVideoLauncher = registerForActivityResult(ActivityResultContracts.CaptureVideo()){
            providerFileManager.insertVideoToStore(videoInfo)
        }

        with(binding){

            // Photo
            photoButton.setOnClickListener {
                isCapturingVideo = false
                checkStoragePermission {
                    openImageCapture()
                }
            }

            // Video
            videoButton.setOnClickListener {
                isCapturingVideo = true
                checkStoragePermission {
                    openVideoCapture()
                }
            }
        }
    }

    private fun openImageCapture(){
        photoInfo = providerFileManager.generatePhotoUri(System.currentTimeMillis())
        takePictureLauncher.launch(photoInfo?.uri)
    }

    private fun checkStoragePermission(onPermissionGranted: () -> Unit) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
            when (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )) {
                PackageManager.PERMISSION_GRANTED -> {
                    onPermissionGranted()
                }
                else -> {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_EXTERNAL_STORAGE
                    )
                }
            }
        } else {
            onPermissionGranted()
        }
    }

    private fun openVideoCapture(){
        videoInfo = providerFileManager.generateVideoUri(System.currentTimeMillis())
        takeVideoLauncher.launch(videoInfo?.uri)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    if (isCapturingVideo) {
                        openVideoCapture()
                    } else {
                        openImageCapture()
                    }
                }
                return
            }

            else -> {
            }
        }
    }
}