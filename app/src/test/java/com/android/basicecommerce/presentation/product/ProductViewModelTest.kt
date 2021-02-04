package com.android.basicecommerce.presentation.product

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.basicecommerce.data.repository.FakeBasicEcommerceRepositoryImpl
import com.android.basicecommerce.getOrAwaitValue
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.usecase.AddPurchasedProductUseCase
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProductViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: DataMapperVM

    private lateinit var sut: ProductViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val fakeRepository = FakeBasicEcommerceRepositoryImpl()
        val addPurchasedProductUseCase = AddPurchasedProductUseCase(fakeRepository)
        sut = ProductViewModel(addPurchasedProductUseCase, mapper)
    }

    @Test
    fun `add purchased product return newly added product id when success insert data`() {
        val product: ProductVM = mock()
        val result = sut.addPurchasedProduct(product).getOrAwaitValue()
        Assert.assertEquals(result, 1L)
    }

}