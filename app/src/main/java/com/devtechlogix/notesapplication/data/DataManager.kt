package com.devtechlogix.notesapplication.data

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.devtechlogix.notesapplication.PAGES
import com.devtechlogix.notesapplication.models.Contributers
import com.google.gson.Gson


/**
 * @Author: Abdul Rehman
 * @Date: 13/03/2024.
 */
object DataManager {
    /**
     * Utility function to load data from JSON Assets folder
     */
    var isDataLoaded = mutableStateOf(false)
    var currentPage = mutableStateOf(PAGES.LIST)
    var currentContributer: Contributers? = null
     var dataList = emptyList<Contributers>()

//    fun loadsData(context: Context): List<Contributers> {
//
//        val fileInString: String =
//            context.assets.open("data.json").bufferedReader().use { it.readText() }
//        dataList = Gson().fromJson(fileInString, Array<Contributers>::class.java).asList()
//        isDataLoaded.value = true
//        return dataList
//    }

    fun switchPages() {
        if (currentPage.value == PAGES.LIST) {
            currentPage.value = PAGES.DETAIL
        } else {
            currentPage.value = PAGES.LIST
        }
    }
}