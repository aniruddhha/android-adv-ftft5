package com.ani.android.hilt.config

import android.app.Application
import androidx.room.Room
import com.ani.android.hilt.login.dao.CarDao
import com.ani.android.hilt.login.repository.LocalRepository
import com.ani.android.hilt.login.repository.RemoteRepository
import com.ani.android.hilt.login.rest.CarRestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, "app_db").build()
    }

    @Provides
    @Singleton
    fun provideDao(db: AppDb): CarDao {
        return db.carDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.getClient()
    }

    @Provides
    @Singleton
    fun provideCarRestApi(retrofit: Retrofit): CarRestApi {
        return retrofit.create(CarRestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: CarDao): LocalRepository {
        return  LocalRepository(dao)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(carRestApi: CarRestApi): RemoteRepository {
        return  RemoteRepository(carRestApi)
    }
}