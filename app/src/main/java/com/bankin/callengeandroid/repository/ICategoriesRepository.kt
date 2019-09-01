package com.bankin.callengeandroid.repository

import com.bankin.callengeandroid.model.Resource
import io.reactivex.Observable


interface ICategoriesRepository {

    fun fetchAllCategories(): Observable<List<Resource>>

    fun fetchCategoriesByParent(parent: String): Observable<List<Resource>>
}