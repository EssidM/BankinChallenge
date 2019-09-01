package com.bankin.challengeandroid.di.module

import com.bankin.challengeandroid.api.CategoriesService
import com.bankin.challengeandroid.repository.ICategoriesRepository
import com.bankin.challengeandroid.repository.impl.CategoriesRepository
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