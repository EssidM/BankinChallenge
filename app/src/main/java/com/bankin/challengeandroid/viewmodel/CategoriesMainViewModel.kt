package com.bankin.challengeandroid.viewmodel

import android.arch.lifecycle.ViewModel
import com.bankin.challengeandroid.repository.ICategoriesRepository
import io.reactivex.Observable
import javax.inject.Inject


class CategoriesMainViewModel @Inject constructor(private var categoryRepo: ICategoriesRepository) : ViewModel() {

    fun getMainCategories(): Observable<List<CategoryViewModel>> {
        return categoryRepo.fetchMainCategories()
            .map { it.map { item -> CategoryViewModel.newInstance(item) } }
    }

    fun getSubcategories(id: Long): Observable<List<CategoryViewModel>> {
        return categoryRepo.fetchSubCategories(id)
            .map { it.map { item -> CategoryViewModel.newInstance(item) } }
    }

    companion object {
        const val TAG = "CategoriesMainViewModel"
    }
}