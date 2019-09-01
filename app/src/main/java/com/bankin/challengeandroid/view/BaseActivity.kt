package com.bankin.challengeandroid.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bankin.callengeandroid.R
import kotlinx.android.synthetic.main.activity_base.*


abstract class BaseActivity : AppCompatActivity() {


    companion object {
        const val FRAGMENT_TAG = "activity_fragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        parseArguments(intent.extras)
    }

    fun bindFragment(toBind: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, toBind)
            .commit()
    }

    protected open fun parseArguments(args: Bundle?) {
        // no-op
    }
}