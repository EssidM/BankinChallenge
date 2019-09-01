package com.bankin.callengeandroid.di.module

import com.bankin.callengeandroid.api.ResourcesService
import com.bankin.callengeandroid.repository.ICategoriesRepository
import com.bankin.callengeandroid.repository.impl.CategoriesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class CategoriesModule {

    @Provides
    fun provideResourcesService(retrofit: Retrofit): ResourcesService {
        return retrofit.create(ResourcesService::class.java)
    }

    @Provides
    fun provideCategoryRepository(resourcesService: ResourcesService): ICategoriesRepository {
        return CategoriesRepository(resourcesService)
    }
}