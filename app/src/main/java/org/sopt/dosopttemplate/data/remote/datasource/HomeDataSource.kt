package org.sopt.dosopttemplate.data.remote.datasource

import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import org.sopt.dosopttemplate.data.remote.service.HomeApiService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeApiService: HomeApiService,
) {
    suspend fun getMainPage(
        page: Int
    ): ResponseUserListDto =
        homeApiService.getMainPage(page)
}