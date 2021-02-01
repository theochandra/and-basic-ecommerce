package com.android.basicecommerce.di.profile

import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.profile.ProfileViewModelFactory
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @ProfileScope
    @Provides
    fun provideProfileViewModelFactory(
        getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
        removePurchasedProductUseCase: RemovePurchasedProductUseCase,
        mapper: DataMapperVM
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(
            getPurchasedProductsUseCase, removePurchasedProductUseCase, mapper)
    }

}