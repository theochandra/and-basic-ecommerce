package com.android.basicecommerce.presentation.login

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.android.domain.usecase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val isLoading = ObservableBoolean()

    fun login(userName: String, password: String) = liveData {
        val isSuccessLogin = loginUseCase.execute(userName, password)
        emit(isSuccessLogin)
    }

    fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

}