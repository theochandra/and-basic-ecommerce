package com.android.basicecommerce.base

import android.app.Application
import com.android.basicecommerce.BuildConfig
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.di.application.*
import com.android.basicecommerce.di.home.HomeSubComponent
import com.android.basicecommerce.di.login.LoginSubComponent
import com.android.basicecommerce.di.product.ProductSubComponent
import com.android.basicecommerce.di.profile.ProfileSubComponent

class BaseApplication : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule())
            .build()
    }

    override fun createLoginSubComponent(): LoginSubComponent {
        return appComponent.loginSubComponent().create()
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }

    override fun createProductSubComponent(): ProductSubComponent {
        return appComponent.productSubComponent().create()
    }

    override fun createProfileSubComponent(): ProfileSubComponent {
        return appComponent.profileSubComponent().create()
    }

}