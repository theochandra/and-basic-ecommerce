package com.android.basicecommerce.di.application

import android.content.Context
import com.android.basicecommerce.di.home.HomeSubComponent
import com.android.basicecommerce.di.login.LoginSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    LoginSubComponent::class,
    HomeSubComponent::class
])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

}