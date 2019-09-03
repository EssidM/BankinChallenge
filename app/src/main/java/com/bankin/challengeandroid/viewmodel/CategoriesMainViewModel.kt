package com.bankin.challengeandroid.viewmodel

import android.arch.lifecycle.ViewModel
import com.bankin.challengeandroid.repository.ICategoriesRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class CategoriesMainViewModel @Inject constructor(private var categoryRepo: ICategoriesRepository) : ViewModel() {

    val categoriesSubject: BehaviorSubject<List<CategoryViewModel>> = BehaviorSubject.create()
    private val disposables = CompositeDisposable()

    fun getMainCategories() {
        val disposable = categoryRepo.fetchMainCategories()
                .map { it.map { item -> CategoryViewModel.newInstance(item) } }
                .doOnNext {
                    categoriesSubject.onNext(it)
                }.subscribe()

        disposables.add(disposable)
    }

    fun getSubcategories(id: Long) {
       val disposable = categoryRepo.fetchSubCategories(id)
                .map {
                    it.map { item -> CategoryViewModel.newInstance(item) }
                }.doOnNext {
                    categoriesSubject.onNext(it)
                }.subscribe()

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }
}