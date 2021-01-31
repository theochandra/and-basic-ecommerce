package com.android.basicecommerce.di.home

import com.android.basicecommerce.di.login.LoginScope
import com.android.basicecommerce.presentation.home.HomeFragment
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {

    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): HomeSubComponent

    }

}