package com.app.composemusicplayer.repositories

import com.app.musicplayer.api.RetrofitInstance
import com.app.musicplayer.models.response.ResponseData
import retrofit2.Response

class Repository {
    suspend fun getSongsL(): Response<ResponseData> {
        return RetrofitInstance.api.getSongs()
    }
}