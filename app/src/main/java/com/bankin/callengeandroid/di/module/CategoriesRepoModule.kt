package com.bankin.callengeandroid.di.module

import com.bankin.callengeandroid.api.CategoriesService
import com.bankin.callengeandroid.repository.ICategoriesRepository
import com.bankin.callengeandroid.repository.impl.CategoriesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CategoriesRepoModule {

    @Singleton
    @Provides
    fun provideCategoriesRepository(categoriesService: CategoriesService): ICategoriesRepository {
        return CategoriesRepository(categoriesService)
    }
}