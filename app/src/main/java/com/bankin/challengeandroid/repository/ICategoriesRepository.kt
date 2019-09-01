package com.bankin.challengeandroid.repository

import com.bankin.challengeandroid.model.Resource
import io.reactivex.Observable


interface ICategoriesRepository {

    fun fetchMainCategories(): Observable<List<Resource>>

    fun fetchCategoriesByParent(parent: String = ""): Observable<List<Resource>>
}