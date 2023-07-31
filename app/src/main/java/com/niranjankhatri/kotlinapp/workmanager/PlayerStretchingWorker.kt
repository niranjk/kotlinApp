package com.niranjankhatri.kotlinapp.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class PlayerStretchingWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters){
    override fun doWork(): Result {
        val playerId = inputData.getString(INPUT_DATA_PLAYER_ID)
        Thread.sleep(3000L)
        val outputData = Data.Builder()
            .putString(OUTPUT_DATA_PLAYER_ID, playerId)
            .build()
        return Result.success(outputData)
    }

    companion object {
        const val INPUT_DATA_PLAYER_ID = "inId"
        const val OUTPUT_DATA_PLAYER_ID = "outId"
    }
}