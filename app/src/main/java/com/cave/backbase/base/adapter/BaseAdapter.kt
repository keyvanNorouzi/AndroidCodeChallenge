package com.cave.backbase.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T>(
    private val inflate: (layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> ViewBinding,
    diffUtilCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, RecyclerView.ViewHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(inflate(LayoutInflater.from(parent.context), parent, false), bindView())

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseAdapter<T>.ItemViewHolder).bindView(getItem(position), position)
    }

    inner class ItemViewHolder(
        val binding: ViewBinding,
        private val bindFunction: (item: T, binding: ViewBinding, Int) -> Unit
    ) : RecyclerView.ViewHolder((binding).root) {

        fun bindView(item: T, position: Int) {
            bindFunction.invoke(item, binding, position)
        }
    }

    abstract fun bindView(): (T, ViewBinding, Int) -> Unit

    private class DiffCallback<K>(
        private val compareItems: (old: K, new: K) -> Boolean,
        private val compareContents: (old: K, new: K) -> Boolean
    ) : DiffUtil.ItemCallback<K>() {
        override fun areItemsTheSame(old: K, new: K) = compareItems(old, new)
        override fun areContentsTheSame(old: K, new: K) = compareContents(old, new)
    }
}
