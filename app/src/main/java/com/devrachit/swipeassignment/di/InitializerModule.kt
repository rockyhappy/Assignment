package com.devrachit.swipeassignment.di

import com.devrachit.swipeassignment.data.local.databases.ApplicationDatabase
import com.devrachit.swipeassignment.data.remote.services.ApiServices
import com.devrachit.swipeassignment.data.repository.local.LocalProductsRepositoryImpl
import com.devrachit.swipeassignment.data.repository.remote.ApiServicesRepositoryImpl
import com.devrachit.swipeassignment.domain.repository.local.LocalProductsRepository
import com.devrachit.swipeassignment.domain.repository.remote.ApiServicesRepository
import org.koin.dsl.module

val initializerModule = module {

    single<LocalProductsRepository> {
        LocalProductsRepositoryImpl(get<ApplicationDatabase>().productItemDao)
    }
    single<ApiServicesRepository> {
        ApiServicesRepositoryImpl(get<ApiServices>())
    }

}