package org.sopt.dosopttemplate.data.remote.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import javax.inject.Inject

class UserPagingSource @Inject constructor(
    private val dataSource: HomeDataSource,
) : PagingSource<Int, ResponseUserListDto.ResponseReqresUserDto>() {

    override fun getRefreshKey(state: PagingState<Int, ResponseUserListDto.ResponseReqresUserDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseUserListDto.ResponseReqresUserDto> {

        val position = params.key ?: 0
        Log.d("hoem", "$position")
        return runCatching {
            val userList = dataSource.getMainPage(position).data
            Log.d("hoem", "$userList")
            LoadResult.Page(
                data = userList,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (userList.isEmpty()) null else position + 1
            )
        }.getOrElse {
            Log.d("hoem", "$it")
            LoadResult.Error(it)
        }
    }
}