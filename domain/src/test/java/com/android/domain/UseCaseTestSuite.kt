package com.android.domain

import com.android.domain.usecase.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AddPurchasedProductUseCaseTest::class,
    GetHomeScreenDataUseCaseTest::class,
    GetPurchasedProductsUseCaseTest::class,
    GetSearchedDataUseCaseTest::class,
    LoginUseCaseTest::class,
    RemovePurchasedProductUseCaseTest::class
)
class UseCaseTestSuite