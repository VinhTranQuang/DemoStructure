package com.android.example.di

import android.app.Application
import androidx.room.Room
import com.android.example.data.source.local.AppDatabase
import com.android.example.data.source.local.dao.TradeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    internal fun providePhotoDao(appDatabase: AppDatabase): TradeDao {
        return appDatabase.tradeDao
    }
}
