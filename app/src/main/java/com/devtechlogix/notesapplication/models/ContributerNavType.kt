package com.devtechlogix.notesapplication.models

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson


/**
 * @Author: Abdul Rehman
 * @Date: 19/04/2024.
 */

class ContributerNavType : NavType<Contributers>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Contributers? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Contributers {
        return Gson().fromJson(value, Contributers::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Contributers) {
        bundle.putParcelable(key, value)
    }
}