package com.devrachit.swipeassignment.domain.usecases

import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.data.remote.dto.toEntity
import com.devrachit.swipeassignment.domain.repository.local.LocalProductsRepository
import com.devrachit.swipeassignment.domain.repository.remote.ApiServicesRepository
import com.devrachit.swipeassignment.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetProductsUseCase (
    private val apiServicesRepository: ApiServicesRepository,
    private val localProductsRepository: LocalProductsRepository
){
    operator fun invoke(isRefresh: Boolean=false): Flow<Resource<List<ProductItem>>> = flow{
//        try{
//            emit(Resource.Loading())
//            val isEmpty = localProductsRepository.fetchAllProducts().first().isEmpty()
//            if(isEmpty || isRefresh){
//                emit(Resource.Loading())
//                val result = apiServicesRepository.getProducts()
//                localProductsRepository.clearTable()
//                localProductsRepository.upsertProductList(result.map{ it.toEntity() })
//                val localCached = localProductsRepository.fetchAllProducts().filterNotNull().first()
//                emit(Resource.Success(localCached))
//            }
//            else{
//                val local = localProductsRepository.fetchAllProducts().filterNotNull().first()
//                emit(Resource.Success(local))
//            }
//
//        }
        try{
            emit(Resource.Loading())
            val isEmpty = localProductsRepository.fetchAllProducts().first().isEmpty()
            if(isEmpty || isRefresh){
                emit(Resource.Loading())
                try {
                    val result = apiServicesRepository.getProducts()
                    localProductsRepository.clearTable()
                    localProductsRepository.upsertProductList(result.map{ it.toEntity() })
                } catch (e: Exception) {
                    emit(Resource.Error("Network error: ${e.localizedMessage}"))
                }
            }
            val localCached = localProductsRepository.fetchAllProducts().filterNotNull().first()
            emit(Resource.Success(localCached))
        }
        catch (e: Exception){
            emit(Resource.Error(e.localizedMessage.toString()))
        }
    }
}