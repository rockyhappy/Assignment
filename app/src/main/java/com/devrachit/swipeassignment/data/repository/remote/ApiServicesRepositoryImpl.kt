package com.devrachit.swipeassignment.data.repository.remote

import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.data.remote.dto.AddProductResponseDto
import com.devrachit.swipeassignment.data.remote.dto.ProductItemDto
import com.devrachit.swipeassignment.data.remote.services.ApiServices
import com.devrachit.swipeassignment.domain.repository.remote.ApiServicesRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ApiServicesRepositoryImpl(
    private val api: ApiServices,
) : ApiServicesRepository {
    override suspend fun getProducts(): List<ProductItemDto> = api.get()


    override suspend fun postProduct(
        productName: RequestBody,
        productType: RequestBody,
        price: RequestBody,
        tax: RequestBody,
        files: List<MultipartBody.Part>,
    ): AddProductResponseDto = api.add(
        productName = productName,
        productType = productType,
        price = price,
        tax = tax,
        files = files
    )
}