package org.sopt.dosopttemplate.domain.repo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto

interface HomeRepo {
    fun getUserList(): Flow<PagingData<ResponseUserListDto.ResponseReqresUserDto>>
}