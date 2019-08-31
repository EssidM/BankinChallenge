package com.bankin.callengeandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// todo convert to data class
class Resource {

    @Expose
    var custom: Boolean? = null
    @Expose
    var id: Long? = null
    @SerializedName("is_deleted")
    var isDeleted: Boolean? = null
    @Expose
    var name: String? = null
    @Expose
    var other: Boolean? = null
    @Expose
    var parent: Parent? = null
    @SerializedName("resource_type")
    var resourceType: String? = null
    @SerializedName("resource_uri")
    var resourceUri: String? = null

}
