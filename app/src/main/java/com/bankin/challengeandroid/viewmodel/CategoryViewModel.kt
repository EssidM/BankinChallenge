package com.bankin.challengeandroid.viewmodel

import com.bankin.challengeandroid.model.Resource


data class CategoryViewModel(val title: String, val id: Long?) {

    companion object {
        fun newInstance(resource: Resource): CategoryViewModel {
            return CategoryViewModel(resource.name ?: "", resource.id)
        }
    }
}