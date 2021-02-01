package com.android.domain.usecase

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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddPurchasedProductUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: AddPurchasedProductUseCase

    private val product = ProductPromo(
        id = 113,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
        title = "nunc mattis",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
        price = "$330",
        loved = 1
    )

    @Before
    fun setup() {
        sut = AddPurchasedProductUseCase(repository)
    }

    @Test
    fun `returns newly added product id when success insert data`() {
        runBlocking {
            `when`(repository.addPurchasedProduct(product))
                    .thenReturn(1L)

            val result = sut.execute(product)

            verify(repository).addPurchasedProduct(product)
            Assert.assertEquals(result, 1L)
        }
    }

    @Test
    fun `returns zero when failed insert data`() {
        runBlocking {
            `when`(repository.addPurchasedProduct(product))
                    .thenReturn(0L)

            val result = sut.execute(product)

            verify(repository).addPurchasedProduct(product)
            Assert.assertEquals(result, 0L)
        }
    }

}