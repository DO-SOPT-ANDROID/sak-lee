package org.sopt.dosopttemplate.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemOtherProfileBinding
import org.sopt.dosopttemplate.presentation.model.OtherProfile

class HomeOtherViewHolder(
    private val binding: ItemOtherProfileBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: OtherProfile) {
        binding.profile = data
        binding.executePendingBindings()
    }
}