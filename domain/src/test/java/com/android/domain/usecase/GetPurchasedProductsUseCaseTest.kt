package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
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
            val productList: List<ProductPromo> = mock()
            given(repository.getPurchasedProducts())
                .willReturn(Result.Success(productList))

            sut.execute()

            verify(repository).getPurchasedProducts()
        }
    }

    @Test
    fun `returns result error when failed get purchased product list`() {
        runBlocking {
            given(repository.getPurchasedProducts())
                .willReturn(Result.Error(404, "error occurred"))

            sut.execute()

            verify(repository).getPurchasedProducts()
        }
    }

    @Test
    fun `return result exception when exception occurred`() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getPurchasedProducts())
                .willReturn(Result.Exception(exception))

            sut.execute()

            verify(repository).getPurchasedProducts()
        }
    }

}