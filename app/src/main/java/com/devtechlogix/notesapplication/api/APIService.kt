package com.devtechlogix.notesapplication.api

import com.devtechlogix.notesapplication.models.Contributers
import retrofit2.Response
import retrofit2.http.GET


/**
 * @Author: Abdul Rehman
 * @Date: 12/03/2024.
 */
interface APIService {
    @GET("contributors")
    suspend fun getContributors(): Response<List<Contributers>>
}