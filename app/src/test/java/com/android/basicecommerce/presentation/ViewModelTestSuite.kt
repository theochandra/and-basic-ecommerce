package com.android.basicecommerce.presentation

import com.android.basicecommerce.presentation.home.HomeViewModelTest
import com.android.basicecommerce.presentation.login.LoginViewModelTest
import com.android.basicecommerce.presentation.mapper.DataMapperVMTest
import com.android.basicecommerce.presentation.product.ProductViewModelTest
import com.android.basicecommerce.presentation.profile.ProfileViewModelTest
import com.android.basicecommerce.presentation.search.SearchViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    HomeViewModelTest::class,
    LoginViewModelTest::class,
    DataMapperVMTest::class,
    ProductViewModelTest::class,
    ProfileViewModelTest::class,
    SearchViewModelTest::class
)
class ViewModelTestSuite