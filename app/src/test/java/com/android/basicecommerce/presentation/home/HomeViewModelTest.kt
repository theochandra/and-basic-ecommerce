package com.android.basicecommerce.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.basicecommerce.data.repository.FakeBasicEcommerceRepositoryImpl
import com.android.basicecommerce.observeOnce
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.Result
import com.android.domain.model.Category
import com.android.domain.model.ProductPromo
import com.android.domain.usecase.GetHomeScreenDataUseCase
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

class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: DataMapperVM

    private lateinit var getHomeScreenDataUseCase: GetHomeScreenDataUseCase

    private lateinit var sut: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val fakeRepository = FakeBasicEcommerceRepositoryImpl()

        getHomeScreenDataUseCase = GetHomeScreenDataUseCase(fakeRepository)

        sut = HomeViewModel(getHomeScreenDataUseCase, mapper)
    }

    @Test
    fun `observe product list when get data return result success`() {
        var listProduct = listOf<ProductPromo>()
        runBlocking {
            when (val result = getHomeScreenDataUseCase.execute()) {
                is Result.Success -> {
                    listProduct = result.data.productPromoList
                }
            }
        }
        sut.productList.observeOnce { listProductVM ->
            assertThat(listProductVM, equalTo(listProduct.map { mapper.map(it) }))
        }
    }

    @Test
    fun `observe category list when get data return result success`() {
        var listCategory = listOf<Category>()
        runBlocking {
            when (val result = getHomeScreenDataUseCase.execute()) {
                is Result.Success -> {
                    listCategory = result.data.categoryList
                }
            }
        }
        sut.categoryList.observeOnce { listCategoryVM ->
            assertThat(listCategoryVM, equalTo(listCategory.map { mapper.map(it) }))
        }
    }

    @Test
    fun `observe error when get data return error`() {
        val errorCode = 404
        val errorMessage = "Error Occurred!"
        val useCase: GetHomeScreenDataUseCase = mock()

        runBlocking {
            given(useCase.execute())
                .willReturn(Result.Error(errorCode, errorMessage))
        }
        sut.error.observeOnce { message ->
            assertThat(message, equalTo(errorMessage))
        }
    }

    @Test
    fun `observe exception when get data return exception`() {
        val exception: Exception = mock()
        val useCase: GetHomeScreenDataUseCase = mock()

        runBlocking {
            given(useCase.execute())
                .willReturn(Result.Exception(exception))
        }
        sut.exception.observeOnce { e ->
            assertThat(e, equalTo(exception))
        }
    }

}