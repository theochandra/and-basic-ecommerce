package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository

class AddPurchasedProductUseCase(private val repository: BasicEcommerceRepository) {

    suspend fun execute(product: ProductPromo): Long = repository.addPurchasedProduct(product)


}