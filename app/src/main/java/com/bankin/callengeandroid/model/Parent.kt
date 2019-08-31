package com.bankin.callengeandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// todo convert to data class
class Parent {

    @Expose
    var id: Long? = null
    @SerializedName("resource_type")
    var resourceType: String? = null
    @SerializedName("resource_uri")
    var resourceUri: String? = null

}
