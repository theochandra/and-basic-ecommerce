package com.android.basicecommerce.di.application

import android.content.Context
import androidx.room.Room
import com.android.data.db.BasicEcommerceDao
import com.android.data.db.BasicEcommerceDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): BasicEcommerceDatabase {
        return Room.databaseBuilder(context, BasicEcommerceDatabase::class.java, "basic_ecommerce_client").build()
    }

    @Singleton
    @Provides
    fun provideBasicEcommerceDao(database: BasicEcommerceDatabase): BasicEcommerceDao {
        return database.basicEcommerceDao()
    }

}