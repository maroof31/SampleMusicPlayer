package com.app.composemusicplayer.repositories

import com.app.composemusicplayer.api.RetrofitInstance
import com.app.composemusicplayer.models.response.ResponseData
import retrofit2.Response

class Repository {
    suspend fun getSongsL(): Response<ResponseData> {
        return RetrofitInstance.api.getSongs()
    }
}