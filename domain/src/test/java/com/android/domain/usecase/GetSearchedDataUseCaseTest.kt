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
class GetSearchedDataUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: GetSearchedDataUseCase

    @Before
    fun setup() {
        sut = GetSearchedDataUseCase(repository)
    }

    @Test
    fun `returns result success when success get searched product list`() {
        runBlocking {
            val productList = listOf<ProductPromo>()

            `when`(repository.getSearchedData())
                    .thenReturn(Result.Success(productList))

            val result = sut.execute()

            verify(repository).getSearchedData()
            Assert.assertEquals(result, Result.Success(productList))
        }
    }

    @Test
    fun `returns result error when failed get searched product list`() {
        runBlocking {
            `when`(repository.getSearchedData())
                    .thenReturn(Result.Error(404, "error occurred"))

            val result = sut.execute()

            verify(repository).getSearchedData()
            Assert.assertEquals(result, Result.Error(404, "error occurred"))
        }
    }

    @Test
    fun `return result exception when searched occurred`() {
        runBlocking {
            val exception = Exception()

            `when`(repository.getSearchedData())
                    .thenReturn(Result.Exception(exception))

            val result = sut.execute()

            verify(repository).getSearchedData()
            Assert.assertEquals(result, Result.Exception(exception))
        }
    }

}