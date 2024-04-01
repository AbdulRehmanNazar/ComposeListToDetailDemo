package com.devtechlogix.notesapplication.repository

import androidx.compose.runtime.mutableStateOf
import com.devtechlogix.notesapplication.api.APIService
import com.devtechlogix.notesapplication.models.Contributers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


/**
 * @Author: Abdul Rehman
 * @Date: 31/03/2024.
 */

class GithubRepo @Inject constructor(private val apiService: APIService) {

    private val _contributers = MutableStateFlow<List<Contributers>>(emptyList())
    val categories: StateFlow<List<Contributers>>
        get() = _contributers

    suspend fun getContributors() {
        val response = apiService.getContributors()
        if (response.isSuccessful && response.body() != null) {
            _contributers.emit(response.body()!!)
        }
    }

}