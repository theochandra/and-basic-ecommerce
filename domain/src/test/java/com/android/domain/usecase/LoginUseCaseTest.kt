package com.android.domain.usecase

import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.repository.BasicEcommerceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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
            `when`(repository.login(username, password))
                .thenReturn(true)

            val result = sut.execute(username, password)

            verify(repository).login(username, password)
            Assert.assertEquals(result, true)
        }
    }

    @Test
    fun `returns false when failed login`() {
        runBlocking {
            `when`(repository.login(username, password))
                    .thenReturn(false)

            val result = sut.execute(username, password)

            verify(repository).login(username, password)
            Assert.assertEquals(result, false)
        }
    }

}