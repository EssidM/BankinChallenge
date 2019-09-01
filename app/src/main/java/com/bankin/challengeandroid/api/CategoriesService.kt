package com.bankin.challengeandroid.api

import com.bankin.challengeandroid.model.Categories
import io.reactivex.Single
import retrofit2.http.GET


interface CategoriesService {

    @GET("categories.json")
    fun getResources(): Single<Categories>
}