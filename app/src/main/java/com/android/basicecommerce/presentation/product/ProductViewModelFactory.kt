package com.android.basicecommerce.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.presentation.mapper.ProductMapperVM
import com.android.domain.usecase.AddPurchasedProductUseCase

class ProductViewModelFactory(
    private val addPurchasedProductUseCase: AddPurchasedProductUseCase,
    private val mapper: ProductMapperVM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(addPurchasedProductUseCase, mapper) as T
    }

}