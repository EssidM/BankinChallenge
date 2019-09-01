package com.bankin.challengeandroid.di.component

import com.bankin.challengeandroid.di.module.AppModule
import com.bankin.challengeandroid.di.module.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface NetworkComponent {

    fun retrofit(): Retrofit

}