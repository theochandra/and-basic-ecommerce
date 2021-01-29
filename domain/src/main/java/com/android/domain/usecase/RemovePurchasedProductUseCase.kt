package com.android.domain.usecase

import com.android.domain.repository.BasicEcommerceRepository

class RemovePurchasedProductUseCase(private val repository: BasicEcommerceRepository) {

    suspend fun execute(productId: Int): Int = repository.removePurchasedProduct(productId)

}