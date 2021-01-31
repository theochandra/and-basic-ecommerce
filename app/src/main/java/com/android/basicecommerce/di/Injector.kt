package com.android.basicecommerce.di

import com.android.basicecommerce.di.home.HomeSubComponent
import com.android.basicecommerce.di.login.LoginSubComponent

interface Injector {

    fun createLoginSubComponent(): LoginSubComponent

    fun createHomeSubComponent(): HomeSubComponent

}