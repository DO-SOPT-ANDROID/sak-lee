package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import org.sopt.dosopttemplate.databinding.ItemOtherProfileBinding
import org.sopt.dosopttemplate.presentation.home.viewholder.HomeMyViewHolder
import org.sopt.dosopttemplate.presentation.home.viewholder.HomeOtherViewHolder
import org.sopt.dosopttemplate.presentation.model.HomeList
import org.sopt.dosopttemplate.presentation.model.MyProfile
import org.sopt.dosopttemplate.presentation.model.OtherProfile
import org.sopt.dosopttemplate.ui.ItemDiffCallback

class HomeAdapter() :
    ListAdapter<HomeList, ViewHolder>(
        HomeDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            1 -> HomeMyViewHolder(
                ItemMyProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            2 -> HomeOtherViewHolder(
                ItemOtherProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            // 확장성을 고려해서 1자리 남겨두었습니다 ㅎ
            else -> HomeOtherViewHolder(
                ItemOtherProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = currentList[position]
        when (holder) {
            is HomeMyViewHolder -> holder.bind(data as MyProfile)
            is HomeOtherViewHolder -> holder.bind(data as OtherProfile)
        }
    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        // 예상
        is MyProfile -> 1
        is OtherProfile -> 2
    }

    companion object {
        private val HomeDiffCallback =
            ItemDiffCallback<HomeList>(
                onItemsTheSame = { old, new -> old.userId == new.userId },
                onContentsTheSame = { old, new -> old == new }
            )
    }

}