package com.devrachit.swipeassignment.di

import com.devrachit.swipeassignment.presentation.screens.addProductScreen.AddProductViewModel
import com.devrachit.swipeassignment.presentation.screens.homeScreen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val viewmodelModule = module {

    viewModel<HomeScreenViewModel> {
        HomeScreenViewModel(get())
    }
    viewModel<AddProductViewModel> {
        AddProductViewModel(get())
    }

}