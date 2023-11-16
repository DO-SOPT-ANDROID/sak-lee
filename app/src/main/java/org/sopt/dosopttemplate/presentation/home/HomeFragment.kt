package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.ui.base.BindingFragment


@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var adapter: HomeAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = HomeAdapter()
        binding.rvHome.adapter = adapter.apply {
            pagingSubmitData(viewLifecycleOwner, homeViewModel.getUserList(), adapter)
        }
    }

}

fun <T : Any> pagingSubmitData(
    lifecycleOwner: LifecycleOwner,
    getData: Flow<PagingData<T>>,
    pagingAdapter: PagingDataAdapter<T, *>
) {
    lifecycleOwner.lifecycleScope.launch {
        getData.collectLatest { pagingData ->
            pagingAdapter.submitData(pagingData)
        }
    }
}