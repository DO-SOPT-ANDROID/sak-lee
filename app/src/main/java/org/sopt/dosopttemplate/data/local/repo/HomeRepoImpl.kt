package org.sopt.dosopttemplate.data.local.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import org.sopt.dosopttemplate.data.remote.datasource.HomeDataSource
import org.sopt.dosopttemplate.data.remote.datasource.UserPagingSource
import org.sopt.dosopttemplate.domain.repo.HomeRepo
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepo {
    override fun getUserList(): Flow<PagingData<ResponseUserListDto.ResponseReqresUserDto>> =
        Pager(PagingConfig(10)) {
            UserPagingSource(homeDataSource)
        }.flow
}
