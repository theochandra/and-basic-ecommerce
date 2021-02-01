package com.android.data.repository

import com.android.data.datasource.BasicEcommerceCacheDataSource
import com.android.data.datasource.BasicEcommerceLocalDataSource
import com.android.data.datasource.BasicEcommerceRemoteDataSource
import com.android.data.entity.Product
import com.android.data.mapper.DataMapper
import com.android.data.response.DataResultResponse
import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import retrofit2.Response.success

@RunWith(MockitoJUnitRunner::class)
class BasicEcommerceRepositoryImplTest {

    @Mock
    lateinit var cacheDataSource: BasicEcommerceCacheDataSource

    @Mock
    lateinit var localDataSource: BasicEcommerceLocalDataSource

    @Mock
    lateinit var remoteDataSource: BasicEcommerceRemoteDataSource

    @Mock
    lateinit var mapper: DataMapper

    private lateinit var sut: BasicEcommerceRepository

    private val username = "username"
    private val password = "password"

    @Before
    fun setup() {
        sut = BasicEcommerceRepositoryImpl(
            cacheDataSource,
            localDataSource,
            remoteDataSource,
            mapper
        )
    }

    @Test
    fun `login success`() {
        runBlocking {
            given(cacheDataSource.loginFromCache(any(), any())).willReturn(true)
            sut.login(username, password)
            verify(cacheDataSource).loginFromCache(any(), any())
        }
    }

    @Test
    fun `login failed`() {
        runBlocking {
            given(cacheDataSource.loginFromCache(any(), any())).willReturn(false)
            sut.login(username, password)
            verify(cacheDataSource).loginFromCache(any(), any())
        }
    }

    @Test
    fun `gets home screen data`() {
        runBlocking {
            given(remoteDataSource.getHomeScreenData()).willReturn(mock())
            sut.getHomeScreenData()
            verify(remoteDataSource).getHomeScreenData()
        }
    }

    @Test
    fun `maps home screen data`() {
        runBlocking {
            val listDataResultResponse = mock<List<DataResultResponse>>()
            val response = success(listDataResultResponse)

            given(remoteDataSource.getHomeScreenData()).willReturn(response)
            given(mapper.map(listDataResultResponse)).willReturn(mock())
            sut.getHomeScreenData()
            verify(mapper).map(eq(listDataResultResponse))
        }
    }

    @Test
    fun `returns mapped home screen data`() {
        runBlocking {
            val listDataResultResponse = mock<List<DataResultResponse>>()
            val response = success(listDataResultResponse)
            val mappedResponse = mock<Data>()

            given(remoteDataSource.getHomeScreenData()).willReturn(response)
            given(mapper.map(listDataResultResponse)).willReturn(mappedResponse)

            val result = sut.getHomeScreenData()
            Assert.assertEquals(Result.Success(mappedResponse), result)
        }
    }

    @Test
    fun `gets purchased products`() {
        runBlocking {
            given(localDataSource.getPurchasedProductsFromDb()).willReturn(mock())
            sut.getPurchasedProducts()
            verify(localDataSource).getPurchasedProductsFromDb()
        }
    }

    @Test
    fun `maps purchased products`() {
        runBlocking {
            val listProduct = mock<List<Product>>()

            given(localDataSource.getPurchasedProductsFromDb()).willReturn(listProduct)
            given(mapper.mapProductEntity(listProduct)).willReturn(mock())

            sut.getPurchasedProducts()
            verify(mapper).mapProductEntity(eq(listProduct))
        }
    }

    @Test
    fun `returns mapped purchased products`() {
        runBlocking {
            val listProduct = mock<List<Product>>()
            val mappedProduct = mock<List<ProductPromo>>()

            given(localDataSource.getPurchasedProductsFromDb()).willReturn(listProduct)
            given(mapper.mapProductEntity(listProduct)).willReturn(mappedProduct)

            val result = sut.getPurchasedProducts()
            Assert.assertEquals(Result.Success(mappedProduct), result)
        }
    }

}