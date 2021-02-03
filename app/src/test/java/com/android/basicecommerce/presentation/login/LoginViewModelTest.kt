package com.android.basicecommerce.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.basicecommerce.data.repository.FakeBasicEcommerceRepositoryImpl
import com.android.basicecommerce.getOrAwaitValue
import com.android.domain.usecase.LoginUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: LoginViewModel

    private val username = "username"
    private val password = "password"

    @Before
    fun setup() {
        val fakeRepository = FakeBasicEcommerceRepositoryImpl()
        val loginUseCase = LoginUseCase(fakeRepository)
        sut = LoginViewModel(loginUseCase)
    }

    @Test
    fun `login returns true when login success`() {
        val result = sut.login(username, password).getOrAwaitValue()
        Assert.assertEquals(result, true)
    }

}