package com.app.composemusicplayer.api

import com.app.composemusicplayer.models.response.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("songs")
    suspend fun getSongs(): Response<ResponseData>
}