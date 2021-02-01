package com.android.basicecommerce.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.mapper.ProductMapperVM
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase

class ProfileViewModelFactory(
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
    private val removePurchasedProductUseCase: RemovePurchasedProductUseCase,
    private val mapper: ProductMapperVM,
    private val mapperVM: DataMapperVM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            getPurchasedProductsUseCase, removePurchasedProductUseCase, mapper, mapperVM) as T
    }

}