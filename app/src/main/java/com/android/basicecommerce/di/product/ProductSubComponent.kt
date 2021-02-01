package com.android.basicecommerce.di.product

import com.android.basicecommerce.presentation.product.ProductActivity
import dagger.Subcomponent

@ProductScope
@Subcomponent(modules = [ProductModule::class])
interface ProductSubComponent {

    fun inject(productActivity: ProductActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(): ProductSubComponent

    }

}