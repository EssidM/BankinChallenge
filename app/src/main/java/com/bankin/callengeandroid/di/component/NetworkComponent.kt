package com.bankin.callengeandroid.di.component

import com.bankin.callengeandroid.api.ResourcesService
import com.bankin.callengeandroid.di.module.AppModule
import com.bankin.callengeandroid.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface NetworkComponent {

    fun resourcesService(): ResourcesService

}