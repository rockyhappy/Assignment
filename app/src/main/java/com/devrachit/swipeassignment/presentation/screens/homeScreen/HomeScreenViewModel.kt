package com.devrachit.swipeassignment.presentation.screens.homeScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.domain.usecases.GetProductsUseCase
import com.devrachit.swipeassignment.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class HomeScreenUiStates(
    val loading: Boolean = false,
    val error: String = "",
    val productsList: List<ProductItem> = emptyList(),
    val isRefreshing: Boolean = false,
    var filteredList: List<ProductItem> = emptyList(),
)

class HomeScreenViewModel(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _uiStates = MutableStateFlow(HomeScreenUiStates())
    val uiStates: StateFlow<HomeScreenUiStates> = _uiStates.asStateFlow()

    fun getData(isRefresh: Boolean, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase(isRefresh).collectLatest {
                when (it) {
                    is Resource.Error -> {
                        //Handle Error
                        deactivateLoading()
                        Log.d("HomeScreenViewModel", "getData: ${it.message}")
                    }

                    is Resource.Loading -> {
                        //Handle Loading
                        activateLoading()
                        Log.d("HomeScreenViewModel", "getData: Loading")
                    }

                    is Resource.Success -> {
                        it.data?.let {
//                            delay(1000)
                            _uiStates.value =
                                HomeScreenUiStates(productsList = it, filteredList = it)
                            deactivateLoading()
                        }
                        Log.d("HomeScreenViewModel", "getData: ${it.data}")
                    }
                }
            }
        }
    }

    fun activateLoading() {
        _uiStates.value = _uiStates.value.copy(loading = true)
    }

    fun deactivateLoading() {
        _uiStates.value = _uiStates.value.copy(loading = false)
    }

    fun filterProducts(query: String) {
        val filtered = if (query.isEmpty()) {
            _uiStates.value.productsList
        } else {
            _uiStates.value.productsList.filter {
                it.productName.contains(query, ignoreCase = true) || it.productType.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
        _uiStates.value = _uiStates.value.copy(filteredList = filtered)
    }

    fun changeRefreshState(isRefreshing: Boolean) {
        _uiStates.value = _uiStates.value.copy(isRefreshing = isRefreshing)
    }
}