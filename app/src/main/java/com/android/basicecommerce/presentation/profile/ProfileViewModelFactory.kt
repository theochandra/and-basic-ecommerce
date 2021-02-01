package com.android.basicecommerce.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase

class ProfileViewModelFactory(
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
    private val removePurchasedProductUseCase: RemovePurchasedProductUseCase,
    private val mapper: DataMapperVM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            getPurchasedProductsUseCase, removePurchasedProductUseCase, mapper) as T
    }

}