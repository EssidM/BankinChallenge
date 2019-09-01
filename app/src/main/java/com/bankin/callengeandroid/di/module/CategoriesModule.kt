package com.bankin.callengeandroid.di.module

import com.bankin.callengeandroid.api.CategoriesService
import com.bankin.callengeandroid.repository.ICategoriesRepository
import com.bankin.callengeandroid.repository.impl.CategoriesRepository
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