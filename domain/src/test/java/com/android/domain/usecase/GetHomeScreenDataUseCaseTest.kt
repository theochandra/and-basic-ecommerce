package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.Data
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
class GetHomeScreenDataUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: BasicEcommerceRepository

    private lateinit var sut: GetHomeScreenDataUseCase

    @Before
    fun setup() {
        sut = GetHomeScreenDataUseCase(repository)
    }

    @Test
    fun `returns result success when success retrieving data`() {
        runBlocking {
            val data = Data(
                categoryList = listOf(),
                productPromoList = listOf()
            )

            `when`(repository.getHomeScreenData())
                .thenReturn(Result.Success(data))

            val result = sut.execute()

            verify(repository).getHomeScreenData()
            Assert.assertEquals(result, Result.Success(data))
        }
    }

    @Test
    fun `returns result error when failed retrieving data`() {
        runBlocking {
            `when`(repository.getHomeScreenData())
                .thenReturn(Result.Error(404, "error occurred"))

            val result = sut.execute()

            verify(repository).getHomeScreenData()
            Assert.assertEquals(result, Result.Error(404, "error occurred"))
        }
    }

    @Test
    fun `returns result exception when exception occurred`() {
        runBlocking {
            val exception = Exception()

            `when`(repository.getHomeScreenData())
                .thenReturn(Result.Exception(exception))

            val result = sut.execute()

            verify(repository).getHomeScreenData()
            Assert.assertEquals(result, Result.Exception(exception))
        }
    }

}