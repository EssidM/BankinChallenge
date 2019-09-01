package com.bankin.challengeandroid.di.component

import com.bankin.challengeandroid.di.module.AppModule
import com.bankin.challengeandroid.di.module.CategoriesModule
import com.bankin.challengeandroid.di.module.NetworkModule
import com.bankin.challengeandroid.view.CategoriesListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, CategoriesModule::class])
interface CategoriesComponent {

    fun inject(categoriesListFragment: CategoriesListFragment)
}