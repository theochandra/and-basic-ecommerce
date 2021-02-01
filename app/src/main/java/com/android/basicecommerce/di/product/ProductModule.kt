package com.android.basicecommerce.di.product

import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.product.ProductViewModelFactory
import com.android.domain.usecase.AddPurchasedProductUseCase
import dagger.Module
import dagger.Provides

@Module
class ProductModule {

    @ProductScope
    @Provides
    fun provideProductViewModelFactory(
        addPurchasedProductUseCase: AddPurchasedProductUseCase,
        mapper: DataMapperVM
    ): ProductViewModelFactory {
        return ProductViewModelFactory(addPurchasedProductUseCase, mapper)
    }

}