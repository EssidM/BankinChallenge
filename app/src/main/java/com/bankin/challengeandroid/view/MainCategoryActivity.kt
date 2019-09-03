package com.bankin.challengeandroid.view

import android.os.Bundle
import com.bankin.callengeandroid.R

class MainCategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.title_main_categories)

        bindFragment(CategoriesListFragment.newInstance())

    }

}
