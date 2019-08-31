package com.bankin.callengeandroid.api

import com.bankin.callengeandroid.model.Resources
import io.reactivex.Single
import retrofit2.http.GET


interface ResourceService {

    @GET
    fun getResources(): Single<Resources>
}