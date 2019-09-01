package com.bankin.callengeandroid.repository.impl

import com.bankin.callengeandroid.api.ResourceService
import com.bankin.callengeandroid.model.Resource
import com.bankin.callengeandroid.repository.ICategoriesRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CategoriesRepository @Inject constructor(private val resourceService: ResourceService) :
    ICategoriesRepository {


    override fun fetchAllCategories(): Observable<List<Resource>> {
        return fetchCategoriesByParent()
    }

    override fun fetchCategoriesByParent(parent: String): Observable<List<Resource>> {
        return resourceService.getResources()
            .subscribeOn(Schedulers.io())
            .map { result ->
                // return category items or empty list
                val categories = result.items ?: ArrayList()

                // filter on parent if defined
                if (parent.isNotEmpty()) {
                    return@map categories.filter { parent.equals(it.parent) }
                }

                // return original list
                return@map categories
            }.toObservable()
    }

}