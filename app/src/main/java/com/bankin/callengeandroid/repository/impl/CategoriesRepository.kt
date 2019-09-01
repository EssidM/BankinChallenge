package com.bankin.callengeandroid.repository.impl

import android.util.Log
import com.bankin.callengeandroid.api.CategoriesService
import com.bankin.callengeandroid.model.Resource
import com.bankin.callengeandroid.repository.ICategoriesRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CategoriesRepository @Inject constructor(private val categoriesService: CategoriesService) :
    ICategoriesRepository {


    override fun fetchAllCategories(): Observable<List<Resource>> {
        return fetchCategoriesByParent()
    }

    override fun fetchCategoriesByParent(parent: String): Observable<List<Resource>> {
        return categoriesService.getResources()
            .subscribeOn(Schedulers.io())
            .map { result ->

                Log.d(TAG, "results : ${result.resources}")

                // return category items or empty list
                val categories = result.resources ?: ArrayList()

                // filter on parent if defined
                if (parent.isNotEmpty()) {
                    return@map categories.filter { parent.equals(it.parent) }
                }

                // return original list
                return@map categories
            }.toObservable()
    }

    companion object {
        const val TAG = "CategoriesRepository"
    }

}