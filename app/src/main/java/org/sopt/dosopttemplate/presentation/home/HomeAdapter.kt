package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import org.sopt.dosopttemplate.presentation.home.viewholder.HomeMyViewHolder
import org.sopt.dosopttemplate.ui.ItemDiffCallback

class HomeAdapter(
) : PagingDataAdapter<ResponseUserListDto.ResponseReqresUserDto, HomeMyViewHolder>(
    HomeDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMyViewHolder {
        val binding =
            ItemMyProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeMyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val HomeDiffCallback =
            ItemDiffCallback<ResponseUserListDto.ResponseReqresUserDto>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}