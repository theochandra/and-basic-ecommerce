package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository

class GetSearchedDataUseCase(private val repository: BasicEcommerceRepository) {

    suspend fun execute(): Result<List<ProductPromo>> = repository.getSearchedData()

}