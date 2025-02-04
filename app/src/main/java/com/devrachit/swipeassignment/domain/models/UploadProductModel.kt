package com.devrachit.swipeassignment.domain.models

import android.net.Uri

data class UploadProductModel(
    val productName: String = "",
    val productType: String = "",
    val price: String = "",
    val tax: String = "",
    val files: List<Uri> = arrayListOf(),
)
