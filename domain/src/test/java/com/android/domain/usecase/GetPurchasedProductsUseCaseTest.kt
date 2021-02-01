package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.ProductPromo
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
class GetPurchasedProductsUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: GetPurchasedProductsUseCase

    @Before
    fun setup() {
        sut = GetPurchasedProductsUseCase(repository)
    }

    @Test
    fun `returns result success when success get purchased product list`() {
        runBlocking {
            val productList = listOf<ProductPromo>()

            `when`(repository.getPurchasedProducts())
                .thenReturn(Result.Success(productList))

            val result = sut.execute()

            verify(repository).getPurchasedProducts()
            Assert.assertEquals(result, Result.Success(productList))
        }
    }

    @Test
    fun `returns result error when failed get purchased product list`() {
        runBlocking {
            `when`(repository.getPurchasedProducts())
                .thenReturn(Result.Error(404, "error occurred"))

            val result = sut.execute()

            verify(repository).getPurchasedProducts()
            Assert.assertEquals(result, Result.Error(404, "error occurred"))
        }
    }

    @Test
    fun `return result exception when exception occurred`() {
        runBlocking {
            val exception = Exception()

            `when`(repository.getPurchasedProducts())
                .thenReturn(Result.Exception(exception))

            val result = sut.execute()

            verify(repository).getPurchasedProducts()
            Assert.assertEquals(result, Result.Exception(exception))
        }
    }

}