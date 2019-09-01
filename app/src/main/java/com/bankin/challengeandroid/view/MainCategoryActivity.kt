package com.bankin.challengeandroid.view

import android.os.Bundle

class MainCategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindFragment(CatgeoriesListFragment())
    }

}
