package com.android.basicecommerce.di

import com.android.basicecommerce.di.home.HomeSubComponent
import com.android.basicecommerce.di.login.LoginSubComponent
import com.android.basicecommerce.di.product.ProductSubComponent
import com.android.basicecommerce.di.profile.ProfileSubComponent
import com.android.basicecommerce.di.search.SearchSubComponent

interface Injector {

    fun createLoginSubComponent(): LoginSubComponent

    fun createHomeSubComponent(): HomeSubComponent

    fun createProductSubComponent(): ProductSubComponent

    fun createProfileSubComponent(): ProfileSubComponent

    fun createSearchSubComponent(): SearchSubComponent

}