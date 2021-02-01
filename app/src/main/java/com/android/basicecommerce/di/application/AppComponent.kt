package com.android.basicecommerce.di.application

import com.android.basicecommerce.di.home.HomeSubComponent
import com.android.basicecommerce.di.login.LoginSubComponent
import com.android.basicecommerce.di.product.ProductSubComponent
import com.android.basicecommerce.di.profile.ProfileSubComponent
import com.android.basicecommerce.di.search.SearchSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CacheDataModule::class,
    DatabaseModule::class,
    LocalDataModule::class,
    MapperModule::class,
    NetModule::class,
    RemoteDataModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface AppComponent {

    fun loginSubComponent(): LoginSubComponent.Factory

    fun homeSubComponent(): HomeSubComponent.Factory

    fun productSubComponent(): ProductSubComponent.Factory

    fun profileSubComponent(): ProfileSubComponent.Factory

    fun searchSubComponent(): SearchSubComponent.Factory

}