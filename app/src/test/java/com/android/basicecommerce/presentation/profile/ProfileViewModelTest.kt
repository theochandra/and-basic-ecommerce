package com.android.basicecommerce.presentation.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.basicecommerce.CoroutineTestRule
import com.android.basicecommerce.LifeCycleTestOwner
import com.android.basicecommerce.data.repository.FakeBasicEcommerceRepositoryImpl
import com.android.basicecommerce.getOrAwaitValue
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.Result
import com.android.domain.model.ProductPromo
import com.android.domain.usecase.GetPurchasedProductsUseCase
import com.android.domain.usecase.RemovePurchasedProductUseCase
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ProfileViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: DataMapperVM

    private val productListObserver: Observer<List<ProductVM>> = mock()
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase = mock()
    private val removePurchasedProductUseCase: RemovePurchasedProductUseCase = mock()

    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner
    private lateinit var sut: ProfileViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
//        val fakeRepository = FakeBasicEcommerceRepositoryImpl()
//        val getPurchasedProductsUseCase = GetPurchasedProductsUseCase(fakeRepository)
//        val removePurchasedProductUseCase = RemovePurchasedProductUseCase(fakeRepository)

        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()

        sut = ProfileViewModel(getPurchasedProductsUseCase,
            removePurchasedProductUseCase, mapper)
        sut.productList.observe(lifeCycleTestOwner, productListObserver)
    }

    @After
    fun tearDown() {
        lifeCycleTestOwner.onDestroy()
    }

    @Test
    fun `get purchased product return result success list product vm`() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            // given
            lifeCycleTestOwner.onResume()
            //Result<List<ProductPromo>>
            val productList = listOf<ProductPromo>()
            When calling getPurchasedProductsUseCase.execute() itReturns Result.Success(productList)

            // when
            sut.getPurchasedProducts()

            // then
            Verify on getPurchasedProductsUseCase that getPurchasedProductsUseCase.execute() was called
            Verify on productListObserver that productListObserver.onChanged(productList.map { mapper.map(it) }) was called
            VerifyNoFurtherInteractions on productListObserver
        }
    }
}