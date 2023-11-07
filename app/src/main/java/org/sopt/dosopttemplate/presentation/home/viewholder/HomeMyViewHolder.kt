package org.sopt.dosopttemplate.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import org.sopt.dosopttemplate.presentation.model.MyProfile

class HomeMyViewHolder(
    private val binding: ItemMyProfileBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: MyProfile) {
        binding.profile = data
        binding.executePendingBindings()
    }
}