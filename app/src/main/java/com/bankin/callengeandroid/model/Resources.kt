package com.bankin.callengeandroid.model

import com.google.gson.annotations.Expose

// todo convert to data class
class Resources {

    @Expose
    var pagination: Pagination? = null
    @Expose
    var items: List<Resource>? = null

}
