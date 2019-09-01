package com.bankin.callengeandroid.api

import com.bankin.callengeandroid.model.Categories
import io.reactivex.Single
import retrofit2.http.GET


interface CategoriesService {

    @GET("categories.json")
    fun getResources(): Single<Categories>
}