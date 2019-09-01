package com.bankin.challengeandroid.repository.impl

import android.util.Log
import com.bankin.challengeandroid.api.CategoriesService
import com.bankin.challengeandroid.model.Categories
import com.bankin.challengeandroid.model.Resource
import com.bankin.challengeandroid.repository.ICategoriesRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CategoriesRepository @Inject constructor(private val categoriesService: CategoriesService) :
    ICategoriesRepository {


    override fun fetchMainCategories(): Observable<List<Resource>> {
        return fetchCategories().map { categories ->
            return@map categories.resources?.filter { it.parent == null } ?: ArrayList()
        }
    }

    override fun fetchSubCategories(id: Long): Observable<List<Resource>> {
        return fetchCategories().map { result ->

            Log.d(TAG, "results : ${result.resources}")

            // return category items or empty list
            val categories = result.resources ?: ArrayList()

            // filter on parent if defined
            return@map categories.filter { id.equals(it.parent?.id) }
        }
    }

    private fun fetchCategories(): Observable<Categories> {
        return categoriesService.getResources()
            .subscribeOn(Schedulers.io()).toObservable()

    }

    companion object {
        const val TAG = "CategoriesRepository"
    }

}