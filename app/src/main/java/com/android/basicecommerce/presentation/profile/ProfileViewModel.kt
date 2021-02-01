package com.android.basicecommerce.presentation.profile

import androidx.lifecycle.*
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.mapper.ProductMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.Result
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
    private val removePurchasedProductUseCase: RemovePurchasedProductUseCase,
    private val mapper: ProductMapperVM,
    private val mapperVM: DataMapperVM
) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductVM>>()
    val productList: LiveData<List<ProductVM>>
        get() = _productList

    init {
        getPurchasedProducts()
    }

    private fun getPurchasedProducts() {
        viewModelScope.launch {
            when (val result = getPurchasedProductsUseCase.execute()) {
                is Result.Success -> {
                    _productList.postValue(result.data.map {
                        mapperVM.map(it)
                    })
                }
                is Result.Error -> {

                }
                is Result.Exception -> {

                }
            }
        }
    }

    fun removePurchasedProduct() {

    }

}