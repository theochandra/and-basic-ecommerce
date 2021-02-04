package com.android.basicecommerce.presentation.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.basicecommerce.data.repository.FakeBasicEcommerceRepositoryImpl
import com.android.basicecommerce.observeOnce
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.Result
import com.android.domain.model.ProductPromo
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfileViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: DataMapperVM

    private lateinit var getPurchasedProductsUseCase: GetPurchasedProductsUseCase
    private lateinit var removePurchasedProductUseCase: RemovePurchasedProductUseCase

    private lateinit var sut: ProfileViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val fakeRepository = FakeBasicEcommerceRepositoryImpl()

        getPurchasedProductsUseCase = GetPurchasedProductsUseCase(fakeRepository)
        removePurchasedProductUseCase = RemovePurchasedProductUseCase(fakeRepository)

        sut = ProfileViewModel(getPurchasedProductsUseCase,
            removePurchasedProductUseCase, mapper)
    }

    @Test
    fun `observe product list when get purchased product return result success`() {
        var listProductPromo = listOf<ProductPromo>()
        runBlocking {
            when (val result = getPurchasedProductsUseCase.execute()) {
                is Result.Success -> {
                    listProductPromo = result.data
                }
            }
        }
        sut.productList.observeOnce { listProductVM ->
            assertThat(listProductVM, equalTo(listProductPromo.map { mapper.map(it) }))
        }
    }

    @Test
    fun `observe error when get purchased product return error`() {
        val errorCode = 404
        val errorMessage = "Error Occurred!"
        val useCase: GetPurchasedProductsUseCase = mock()

        runBlocking {
            given(useCase.execute())
                .willReturn(Result.Error(errorCode, errorMessage))
        }
        sut.error.observeOnce { message ->
            assertThat(message, equalTo(errorMessage))
        }
    }

    @Test
    fun `observe exception when get purchased product return exception`() {
        val exception: Exception = mock()
        val useCase: GetPurchasedProductsUseCase = mock()

        runBlocking {
            given(useCase.execute())
                .willReturn(Result.Exception(exception))
        }
        sut.exception.observeOnce { e ->
            assertThat(e, equalTo(exception))
        }
    }

}