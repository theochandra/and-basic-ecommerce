package com.android.basicecommerce.di

import com.android.basicecommerce.di.home.HomeSubComponent
import com.android.basicecommerce.di.login.LoginSubComponent
import com.android.basicecommerce.di.product.ProductSubComponent
import com.android.basicecommerce.di.profile.ProfileSubComponent

interface Injector {

    fun createLoginSubComponent(): LoginSubComponent

    fun createHomeSubComponent(): HomeSubComponent

    fun createProductSubComponent(): ProductSubComponent

    fun createProfileSubComponent(): ProfileSubComponent

}