package com.bankin.challengeandroid.di.module

import com.bankin.challengeandroid.api.CategoriesService
import com.bankin.challengeandroid.repository.ICategoriesRepository
import com.bankin.challengeandroid.repository.impl.CategoriesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class CategoriesModule {

    @Provides
    fun provideResourcesService(retrofit: Retrofit): CategoriesService {
        return retrofit.create(CategoriesService::class.java)
    }

    @Provides
    fun provideCategoryRepository(categoriesService: CategoriesService): ICategoriesRepository {
        return CategoriesRepository(categoriesService)
    }
}