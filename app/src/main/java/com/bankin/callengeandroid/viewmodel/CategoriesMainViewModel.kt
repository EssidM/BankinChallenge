package com.bankin.callengeandroid.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.bankin.callengeandroid.repository.ICategoriesRepository
import javax.inject.Inject


class CategoriesMainViewModel @Inject constructor(private var categoryRepo: ICategoriesRepository) : ViewModel() {


    fun getAllCategories() {
        categoryRepo.fetchAllCategories().doOnNext {

            Log.d(TAG, "categories fetched $it")
        }.doOnError {
            it.printStackTrace()
        }.subscribe()
    }

    fun getSubcategories(parent: String) {
        categoryRepo.fetchCategoriesByParent(parent).doOnNext {
            Log.d(TAG, "categories fetched $it")
        }
    }

    companion object {
        const val TAG = "CategoriesMainViewModel"
    }
}