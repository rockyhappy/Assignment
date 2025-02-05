package com.devrachit.swipeassignment.domain.usecases

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.devrachit.swipeassignment.domain.models.UploadProductModel
import com.devrachit.swipeassignment.domain.workers.BackGroundWorker
import com.devrachit.swipeassignment.domain.workers.WorkerCons
import java.util.UUID


class PostProductUseCase {
    operator fun invoke(
        uid: UUID,
        values :UploadProductModel,
        context: Context,
    ) {
        try {

            val workManager = WorkManager.getInstance(context)
            val inputData = Data.Builder()
                .putString(WorkerCons.price, values.price)
                .putString(WorkerCons.tax, values.tax)
                .putString(WorkerCons.productName, values.productName)
                .putString(WorkerCons.productType, values.productType)
                .putStringArray(
                    WorkerCons.listOfUris,
                    values.files.map { it.toString() }.toTypedArray()
                ).build()
            val workRequest = OneTimeWorkRequestBuilder<BackGroundWorker>()
                .setInputData(inputData)
                .setId(uid)
                .setConstraints(
                    Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                )
                .build()
            workManager.enqueueUniqueWork("Upload Post", ExistingWorkPolicy.KEEP, workRequest)

        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}