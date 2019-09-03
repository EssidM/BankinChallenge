package com.bankin.challengeandroid.view

import android.os.Bundle
import com.bankin.callengeandroid.R
import com.bankin.challengeandroid.Arguments

class SubCategoryActivity : BaseActivity() {

    private var id : Long = 0
    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = name
        bindFragment(CategoriesListFragment.newInstance(id))
    }

    override fun parseArguments(args: Bundle?) {
        super.parseArguments(args)

        id = args?.getLong(Arguments.PARENT_CATEGORY_ID) ?: 0
        name = args?.getString(Arguments.PARENT_CATEGORY_NAME) ?: getString(R.string.error)
    }
}
