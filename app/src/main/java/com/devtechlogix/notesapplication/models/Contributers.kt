package com.devtechlogix.notesapplication.models

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable


/**
 * @Author: Abdul Rehman
 * @Date: 13/03/2024.
 */

@Parcelize
data class Contributers(
    var login: String,
    var url: String,
    @SerializedName("avatar_url") var imageURL: String
) :Parcelable