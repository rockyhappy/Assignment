package com.devrachit.swipeassignment.presentation.screens.addProductScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.swipeassignment.domain.models.UploadProductModel
import com.devrachit.swipeassignment.domain.usecases.PostProductUseCase
import com.devrachit.swipeassignment.presentation.screens.homeScreen.HomeScreenUiStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

data class AddProductUiStates(
    val loading: Boolean = false,
    val error: String = "",
    val uiState :UploadProductModel = UploadProductModel()
)
class AddProductViewModel(
    private val postProductUseCase: PostProductUseCase
):ViewModel() {
    private val _uiStates = MutableStateFlow(AddProductUiStates())
    val uiStates: StateFlow<AddProductUiStates> = _uiStates.asStateFlow()

    suspend fun postProduct(
        context : Context,
        product:UploadProductModel
    ): Any {
        return withContext(Dispatchers.IO)  {
            try {
                _uiStates.value = _uiStates.value.copy(loading = true)
                val uid = UUID.nameUUIDFromBytes(product.productName.toByteArray())
                postProductUseCase(context=context, uid =uid , values = product)
                _uiStates.value = _uiStates.value.copy(loading = false)
                true
            }
            catch (e:Exception){
                _uiStates.value = _uiStates.value.copy(error = e.message.toString())
                false
            }
        }
    }
}