package com.bankin.challengeandroid.view

import android.os.Bundle
import android.support.v4.app.Fragment


abstract class BaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parseArguments(arguments)
    }

    protected open fun parseArguments(args : Bundle?) {
        // no-op
    }
}