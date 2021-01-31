package com.android.basicecommerce.di.application

import com.android.domain.repository.BasicEcommerceRepository
import com.android.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        repository: BasicEcommerceRepository
    ): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideAddPurchasedProductUseCase(
        repository: BasicEcommerceRepository
    ): AddPurchasedProductUseCase {
        return AddPurchasedProductUseCase(repository)
    }

    @Provides
    fun provideGetHomeScreenDataUseCase(
        repository: BasicEcommerceRepository
    ): GetHomeScreenDataUseCase {
        return GetHomeScreenDataUseCase(repository)
    }

    @Provides
    fun provideGetPurchasedProductsUseCase(
        repository: BasicEcommerceRepository
    ): GetPurchasedProductsUseCase {
        return GetPurchasedProductsUseCase(repository)
    }

    @Provides
    fun provideGetSearchedDataUseCase(
        repository: BasicEcommerceRepository
    ): GetSearchedDataUseCase {
        return GetSearchedDataUseCase(repository)
    }

    @Provides
    fun provideRemovePurchasedProductUseCase(
        repository: BasicEcommerceRepository
    ): RemovePurchasedProductUseCase {
        return RemovePurchasedProductUseCase(repository)
    }

}