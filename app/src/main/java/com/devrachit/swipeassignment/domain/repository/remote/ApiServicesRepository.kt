package com.devrachit.swipeassignment.domain.repository.remote

import com.devrachit.swipeassignment.data.remote.dto.AddProductResponseDto
import com.devrachit.swipeassignment.data.remote.dto.ProductItemDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ApiServicesRepository {
    suspend fun getProducts(): List<ProductItemDto>
    suspend fun postProduct(
        productName: RequestBody,
        productType: RequestBody,
        price: RequestBody,
        tax: RequestBody,
        files: List<MultipartBody.Part>,
    ): AddProductResponseDto
}