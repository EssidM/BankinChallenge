package com.bankin.callengeandroid.di.component

import com.bankin.callengeandroid.di.module.AppModule
import com.bankin.callengeandroid.di.module.CategoriesModule
import com.bankin.callengeandroid.di.module.NetworkModule
import com.bankin.callengeandroid.view.MainCategoryActivity
import com.bankin.callengeandroid.view.SubCategoryActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, CategoriesModule::class])
interface CategoriesComponent {

    fun inject(target: MainCategoryActivity)
    fun inject(target: SubCategoryActivity)

}