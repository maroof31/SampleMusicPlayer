package com.app.musicplayer.api

import com.app.musicplayer.models.response.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("songs")
    suspend fun getSongs(): Response<ResponseData>
}