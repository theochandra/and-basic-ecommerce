package com.android.basicecommerce.presentation.login

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.android.domain.usecase.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean>
        get() = _isFormValid

    private fun validateForm() {
        if (userName.value?.length!! > 0 && password.value?.length!! > 0) {
            _isFormValid.postValue(true)
        } else {
            _isFormValid.postValue(false)
        }
    }

    val userName = MutableLiveData<String>()
//        set(value) {
//            field = value
//            validateForm()
//        }
    fun getUserName(): LiveData<String> {
        return userName
    }

    val password = MutableLiveData<String>()
//        set(value) {
//            field = value
//            validateForm()
//        }
    fun getPassword(): LiveData<String> {
        return password
    }

    val isLoading = ObservableBoolean()

    fun login(userName: String, password: String) = liveData {
        val isSuccessLogin = loginUseCase.execute(userName, password)
        emit(isSuccessLogin)
    }

    fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

}