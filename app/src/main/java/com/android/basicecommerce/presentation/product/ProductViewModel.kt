package com.android.basicecommerce.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.usecase.AddPurchasedProductUseCase

class ProductViewModel(
    private val addPurchasedProductUseCase: AddPurchasedProductUseCase,
    private val mapper: DataMapperVM
) : ViewModel() {

    fun addPurchasedProduct(product: ProductVM) = liveData {
        val newPurchasedProductId = addPurchasedProductUseCase.execute(mapper.map(product))
        emit(newPurchasedProductId)
    }

}