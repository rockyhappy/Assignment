package com.devrachit.swipeassignment.domain.repository.remote

import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.data.remote.dto.ProductItemDto

interface ApiServicesRepository {
    suspend fun getProducts(): List<ProductItemDto>
//    suspend fun postProduct(
//        productName: RequestBody,
//        productType: RequestBody,
//        price: RequestBody,
//        tax: RequestBody,
//        files: List<MultipartBody.Part>,
//    ): SentResponse
}