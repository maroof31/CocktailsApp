package com.example.cocktails.di

import com.example.cocktails.data.datasource.remote.DrinksApi
import com.example.cocktails.data.repository.DrinksRepository
import com.example.cocktails.data.repository.DrinksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideDrinksRepositoryImpl(
        drinksApi: DrinksApi,
    ): DrinksRepository {
        return DrinksRepositoryImpl(
            drinksApi
        )
    }

}