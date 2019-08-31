package com.bankin.callengeandroid.model

import com.google.gson.annotations.SerializedName

// todo convert to data class
class Pagination {

    @SerializedName("next_uri")
    var nextUri: Any? = null
    @SerializedName("previous_uri")
    var previousUri: Any? = null

}
