package com.bankin.callengeandroid

import android.app.Application
import com.bankin.callengeandroid.api.ResourcesService
import com.bankin.callengeandroid.di.component.DaggerNetworkComponent
import com.bankin.callengeandroid.di.component.NetworkComponent
import com.bankin.callengeandroid.di.module.AppModule
import com.bankin.callengeandroid.di.module.NetworkModule


class BankinChallengeApp : Application() {

    private lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()

        networkComponent = DaggerNetworkComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule("https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/categories.json"))
            .build()
    }

    fun getResourceService(): ResourcesService {
        return networkComponent.resourcesService()
    }
}