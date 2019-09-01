package com.bankin.challengeandroid.repository

import com.bankin.challengeandroid.model.Resource
import io.reactivex.Observable


interface ICategoriesRepository {

    fun fetchMainCategories(): Observable<List<Resource>>

    fun fetchSubCategories(id: Long): Observable<List<Resource>>
}