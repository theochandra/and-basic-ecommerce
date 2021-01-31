package com.android.domain.usecase

import com.android.domain.repository.BasicEcommerceRepository

/**
 * this use case is made to simulate login
 */
class LoginUseCase(private val repository: BasicEcommerceRepository) {

    suspend fun execute(userName:String, password: String): Boolean =
        repository.login(userName, password)

}