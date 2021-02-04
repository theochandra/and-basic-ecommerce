package com.android.domain.usecase

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
class RemovePurchasedProductUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: RemovePurchasedProductUseCase

    private val product: ProductPromo = mock()

    @Before
    fun setup() {
        sut = RemovePurchasedProductUseCase(repository)
    }

    @Test
    fun `returns total row removed when success remove data`() {
        runBlocking {
            given(repository.removePurchasedProduct(product))
                .willReturn(1)

            sut.execute(product)

            verify(repository).removePurchasedProduct(product)
        }
    }

    @Test
    fun `returns zero when failed remove data`() {
        runBlocking {
            given(repository.removePurchasedProduct(product))
                .willReturn(0)

            sut.execute(product)

            verify(repository).removePurchasedProduct(product)
        }
    }

}