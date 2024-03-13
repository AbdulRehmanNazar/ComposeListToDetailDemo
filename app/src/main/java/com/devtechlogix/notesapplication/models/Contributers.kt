package com.devtechlogix.notesapplication.models

import com.google.gson.annotations.SerializedName


/**
 * @Author: Abdul Rehman
 * @Date: 13/03/2024.
 */
data class Contributers(
    var login: String,
    var url: String,
    @SerializedName("avatar_url") var imageURL: String
)