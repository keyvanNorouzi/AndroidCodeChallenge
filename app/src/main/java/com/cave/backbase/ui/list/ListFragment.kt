package com.cave.backbase.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cave.backbase.base.fragment.BaseFragment
import com.cave.backbase.databinding.FragmentListBinding
import kotlin.reflect.KClass

class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>() {

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding = FragmentListBinding::inflate

    override fun getViewModelJavaClass(): KClass<ListViewModel> = ListViewModel::class
}
