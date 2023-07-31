package com.niranjankhatri.kotlinapp.persistingdata.copy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.niranjankhatri.kotlinapp.databinding.ActivityCopyBinding
import java.util.concurrent.Executors

class CopyActivity : AppCompatActivity() {

    lateinit var binding : ActivityCopyBinding

    private val assetFileManager : AssetFileManager by lazy {
        AssetFileManager(applicationContext.assets)
    }

    private val providerFileManager: ProviderFileManager by lazy {
        ProviderFileManager(
            applicationContext,
            fileToUriMapper = FileToUriMapper(),
            executor = Executors.newSingleThreadExecutor()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCopyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            //1.  file provider
            activityMainFileProvider.setOnClickListener {
                val newFileName = "Copied.txt"
                providerFileManager.writeStream(
                    newFileName,
                    assetFileManager.getMyAppFileInputStream()
                )
            }

            // 2. saf
            val createDocumentResult =
                registerForActivityResult(ActivityResultContracts.CreateDocument(
                    "text/plain"
                )){ uri ->
                    uri?.let {
                        val newFileName = "Copied2.txt"
                        providerFileManager.writeStreamFromUri(
                            newFileName,
                            assetFileManager.getMyAppFileInputStream(),
                            uri
                        )
                    }
                }
            activityMainSaf.setOnClickListener {
                createDocumentResult.launch("Copied2.txt")
            }
        }
    }
}