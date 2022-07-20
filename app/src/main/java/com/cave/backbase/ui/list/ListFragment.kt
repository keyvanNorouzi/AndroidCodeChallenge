package com.cave.backbase.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cave.backbase.base.fragment.BaseFragment
import com.cave.backbase.databinding.FragmentListBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    private val viewModel: ListViewModel by stateViewModel()

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding = FragmentListBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
    }
}
