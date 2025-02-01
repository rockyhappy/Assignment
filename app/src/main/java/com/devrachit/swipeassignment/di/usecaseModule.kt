package com.devrachit.swipeassignment.di

import com.devrachit.swipeassignment.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetProductsUseCase> {
        GetProductsUseCase(get(),get())
    }
//    single<PostProductUseCase> {
//        PostProductUseCase()
//    }
//    single<LocalProductsUseCase> {
//        LocalProductsUseCase(get())
//    }
//    single<GetProductByIdUseCase> {
//        GetProductByIdUseCase(get())
//    }
}