package com.bankin.callengeandroid

import android.app.Application
import com.bankin.callengeandroid.di.component.CategoriesComponent
import com.bankin.callengeandroid.di.component.DaggerCategoriesComponent
import com.bankin.callengeandroid.di.module.AppModule
import com.bankin.callengeandroid.di.module.CategoriesModule
import com.bankin.callengeandroid.di.module.NetworkModule


class BankinChallengeApp : Application() {

    private lateinit var appComponent: CategoriesComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerCategoriesComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule("https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/"))
            .categoriesModule(CategoriesModule())
            .build()
    }


    fun getCategoriesComponent(): CategoriesComponent {
        return appComponent
    }
}