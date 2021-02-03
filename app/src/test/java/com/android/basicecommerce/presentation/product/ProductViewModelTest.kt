package com.android.basicecommerce.presentation.product

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.basicecommerce.data.repository.FakeBasicEcommerceRepositoryImpl
import com.android.basicecommerce.getOrAwaitValue
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.usecase.AddPurchasedProductUseCase
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
        val product = ProductVM(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
        val result = sut.addPurchasedProduct(product).getOrAwaitValue()
        Assert.assertEquals(result, 1L)
    }

}