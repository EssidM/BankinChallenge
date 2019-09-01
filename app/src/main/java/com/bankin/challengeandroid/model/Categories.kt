package com.bankin.challengeandroid.model

import com.google.gson.annotations.Expose

// todo convert to data class
class Categories {

    @Expose
    var pagination: Pagination? = null

    @Expose
    var resources: List<Resource>? = null

}
