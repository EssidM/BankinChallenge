package com.bankin.challengeandroid.view

import android.os.Bundle
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.Arguments

class SubCategoryActivity : BaseActivity() {

     var id : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindFragment(CategoriesListFragment.newInstance(id))
    }

    override fun parseArguments(args: Bundle?) {
        super.parseArguments(args)

        id = args?.getLong(Arguments.PARENT_CATEGORY_ID) ?: 0
    }
}
