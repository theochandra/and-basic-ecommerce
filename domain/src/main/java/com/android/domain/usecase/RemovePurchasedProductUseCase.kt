package com.android.domain.usecase

import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository

class RemovePurchasedProductUseCase(private val repository: BasicEcommerceRepository) {

    suspend fun execute(product: ProductPromo): Int = repository.removePurchasedProduct(product)

}