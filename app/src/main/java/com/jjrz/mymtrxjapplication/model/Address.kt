package com.jjrz.mymtrxjapplication.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    var city: String?,
    @SerializedName("geo")
    var geo: Geo?,
    @SerializedName("street")
    var street: String?,
    @SerializedName("suite")
    var suite: String?,
    @SerializedName("zipcode")
    var zipcode: String?
)