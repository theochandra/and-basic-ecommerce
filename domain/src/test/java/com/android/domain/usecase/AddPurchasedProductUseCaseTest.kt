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
class AddPurchasedProductUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: AddPurchasedProductUseCase

    private val product: ProductPromo = mock()

    @Before
    fun setup() {
        sut = AddPurchasedProductUseCase(repository)
    }

    @Test
    fun `returns newly added product id when success insert data`() {
        runBlocking {
            given(repository.addPurchasedProduct(product))
                .willReturn(1L)

            sut.execute(product)

            verify(repository).addPurchasedProduct(product)
        }
    }

    @Test
    fun `returns zero when failed insert data`() {
        runBlocking {
            given(repository.addPurchasedProduct(product))
                .willReturn(0L)

            sut.execute(product)

            verify(repository).addPurchasedProduct(product)
        }
    }

}