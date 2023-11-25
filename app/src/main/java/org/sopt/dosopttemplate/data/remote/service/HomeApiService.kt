package org.sopt.dosopttemplate.data.remote.service

import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {
    @GET("api/users")
    suspend fun getMainPage(
        @Query("page") page: Int,
    ): ResponseUserListDto
}