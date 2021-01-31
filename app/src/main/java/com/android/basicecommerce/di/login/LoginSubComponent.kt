package com.android.basicecommerce.di.login

import com.android.basicecommerce.presentation.login.LoginActivity
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = [LoginModule::class])
interface LoginSubComponent {

    fun inject(loginActivity: LoginActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(): LoginSubComponent

    }

}