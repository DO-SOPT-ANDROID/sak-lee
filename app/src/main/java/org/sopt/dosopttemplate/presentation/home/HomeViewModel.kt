package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import org.sopt.dosopttemplate.domain.repo.HomeRepo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: HomeRepo
) : ViewModel() {
    fun getUserList(): Flow<PagingData<ResponseUserListDto.ResponseReqresUserDto>> =
        apiRepository.getUserList()
}