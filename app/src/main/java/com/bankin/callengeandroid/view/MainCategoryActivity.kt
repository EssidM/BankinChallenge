package com.bankin.callengeandroid.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bankin.callengeandroid.BankinChallengeApp
import com.bankin.callengeandroid.R
import com.bankin.callengeandroid.viewmodel.CategoriesMainViewModel
import javax.inject.Inject

class MainCategoryActivity : AppCompatActivity() {

    @Inject
    lateinit var categoriesMainViewModel: CategoriesMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_category)

        // injecting categories component
        (application as BankinChallengeApp).getCategoriesComponent().inject(this)

        categoriesMainViewModel.getAllCategories()
    }

}
