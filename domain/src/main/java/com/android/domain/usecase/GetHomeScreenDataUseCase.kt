package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.repository.BasicEcommerceRepository

class GetHomeScreenDataUseCase(private val repository: BasicEcommerceRepository) {

    suspend fun execute(): Result<Data> = repository.getHomeScreenData()

}