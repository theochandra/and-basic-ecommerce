package com.android.basicecommerce.di.login

import com.android.basicecommerce.presentation.login.LoginViewModelFactory
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @LoginScope
    @Provides
    fun provideLoginViewModelFactory(
        loginUseCase: LoginUseCase,
        mapperVM: DataMapperVM
    ): LoginViewModelFactory {
        return LoginViewModelFactory(loginUseCase)
    }

}