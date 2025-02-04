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
            // transform the given inputs into the Request Body.
//            val productName = uiState.productName.toRequestBody("text/plain".toMediaTypeOrNull())
//            val productPrice =
//                uiState.price.replace(",", "").toRequestBody("text/plain".toMediaTypeOrNull())
//            val tax = uiState.tax.toRequestBody("text/plain".toMediaTypeOrNull())
//            val type = uiState.productType.toRequestBody("text/plain".toMediaTypeOrNull())
//
//            // transform the given files Media into the Request Body.
//            val fileParts = uiState.files.map {
//                val file = File(FileUtils.getPath(context, it))
//                val requestFile = file
//                    .asRequestBody(context.contentResolver.getType(it)
//                        ?.let { it1 -> it1.toMediaTypeOrNull() })
//
//                //create the Multipart from the given Uri's.
//                MultipartBody.Part.createFormData("files[]", file.name, requestFile)
//            }
//            // get the send state after posting to the server.
////            val sendState = remoteProductsRepo.postProduct(
//                productName = productName,
//                productType = type,
//                price = productPrice,
//                tax = tax,
//                files = fileParts
//            )
//            // if it's success then close the bottom sheet.
//            if (sendState.success) {
//                Log.d("Product", "invoke: $sendState ")
//            }
//            emit(SendState(sentData = sendState))

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