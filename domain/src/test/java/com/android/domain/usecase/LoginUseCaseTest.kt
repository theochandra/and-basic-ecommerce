package com.android.domain.usecase

import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.repository.BasicEcommerceRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: LoginUseCase

    private val username = "username"
    private val password = "password"

    @Before
    fun setup() {
        sut = LoginUseCase(repository)
    }

    @Test
    fun `returns true when success login`() {
        runBlocking {
            given(repository.login(any(), any()))
                .willReturn(true)

            sut.execute(username, password)

            verify(repository).login(any(), any())
        }
    }

    @Test
    fun `returns false when failed login`() {
        runBlocking {
            given(repository.login(any(), any()))
                .willReturn(false)

            sut.execute(username, password)

            verify(repository).login(any(), any())
        }
    }

}