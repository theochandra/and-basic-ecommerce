package com.android.basicecommerce.presentation.profile

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.Result
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
    private val removePurchasedProductUseCase: RemovePurchasedProductUseCase,
    private val mapper: DataMapperVM
) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductVM>>()
    val productList: LiveData<List<ProductVM>>
        get() = _productList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    private val _rowDeleted = MutableLiveData<Int>()
    val rowDeleted: LiveData<Int>
        get() = _rowDeleted

    val isLoading = ObservableBoolean()

    init {
        getPurchasedProducts()
    }

    @VisibleForTesting
    fun getPurchasedProducts() {
        changeLoadingState(true)
        viewModelScope.launch {
            when (val result = getPurchasedProductsUseCase.execute()) {
                is Result.Success -> {
                    _productList.postValue(result.data.map {
                        mapper.map(it)
                    })
                }
                is Result.Error -> {
                    _error.postValue(result.errorMessage)
                }
                is Result.Exception -> {
                    _exception.postValue(result.exception)
                }
            }
            changeLoadingState(false)
        }
    }

    fun removePurchasedProduct(product: ProductVM) {
        changeLoadingState(true)
        viewModelScope.launch {
            _rowDeleted.postValue(
                removePurchasedProductUseCase.execute(mapper.map(product)))
            changeLoadingState(false)
        }
    }

    private fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

}