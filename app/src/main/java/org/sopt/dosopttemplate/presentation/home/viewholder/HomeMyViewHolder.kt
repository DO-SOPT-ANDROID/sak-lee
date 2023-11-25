package org.sopt.dosopttemplate.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.model.response.ResponseUserListDto
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding

class HomeMyViewHolder(
    private val binding: ItemMyProfileBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ResponseUserListDto.ResponseReqresUserDto?) {
        binding.profile = data
        binding.executePendingBindings()
    }
}