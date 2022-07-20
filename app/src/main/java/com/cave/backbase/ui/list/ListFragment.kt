package com.cave.backbase.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cave.backbase.base.fragment.BaseFragment
import com.cave.backbase.data.model.City
import com.cave.backbase.data.model.Result
import com.cave.backbase.databinding.FragmentListBinding
import com.cave.backbase.databinding.ItemCityListBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {
    private val viewModel: ListViewModel by stateViewModel()
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var pastVisibleItems = 0
    private var loading = false
    private var currentPage = 1

    private val cities by lazy {
        ArrayList<City>()
    }

    private val cityAdapter: ListAdapter =
        ListAdapter(
            inflate = { li, parent, attach ->
                ItemCityListBinding.inflate(
                    li,
                    parent,
                    attach
                )
            }
        )

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding =
        FragmentListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        binding.rvList.adapter = cityAdapter
        initPaging()
        getListData()
    }

    private fun getListData() {
        lifecycleScope.launchWhenStarted {
            viewModel.getListData(cities.size).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        shodDataLoading()
                    }
                    is Result.Success -> {
                        dismissDataLoading()
                        result.data?.let {
                            if (cities.size < 1) {
                                cities.addAll(it)
                                cityAdapter.submitList(cities)
                            } else {
                                val position = cities.size
                                cities.addAll(it)
                                cityAdapter.notifyItemRangeInserted(position, it.size)
                            }
                        }
                    }
                    is Result.Error -> {
                        dismissDataLoading()
                        loading = false
                    }
                }
            }
        }
    }

    private fun shodDataLoading() {
        binding.pbLoading.visibility = View.VISIBLE
        loading = true
    }

    private fun dismissDataLoading() {
        binding.pbLoading.visibility = View.GONE
        loading = false
    }

    private fun initPaging() {
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) {
                    if (dy > 0) {
                        val layoutManager =
                            (binding.rvList.layoutManager as LinearLayoutManager)
                        visibleItemCount = layoutManager.childCount
                        totalItemCount = layoutManager.itemCount
                        pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                        if (totalItemCount >= 10) {
                            if (!loading) {
                                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                    currentPage += 1
                                    loading = true
                                    getListData()
                                }
                            }
                        }
                    }
                }
            }
        })
    }
}
