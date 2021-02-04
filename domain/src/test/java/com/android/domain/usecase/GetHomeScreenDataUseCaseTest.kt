package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.Data
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
            val data: Data = mock()
            given(repository.getHomeScreenData())
                .willReturn(Result.Success(data))

            sut.execute()

            verify(repository).getHomeScreenData()
        }
    }

    @Test
    fun `returns result error when failed retrieving data`() {
        runBlocking {
            given(repository.getHomeScreenData())
                .willReturn(Result.Error(404, "error occurred"))

            sut.execute()

            verify(repository).getHomeScreenData()
        }
    }

    @Test
    fun `returns result exception when exception occurred`() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getHomeScreenData())
                .willReturn(Result.Exception(exception))

            sut.execute()

            verify(repository).getHomeScreenData()
        }
    }

}