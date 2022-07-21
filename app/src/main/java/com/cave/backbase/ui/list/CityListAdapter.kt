package com.cave.backbase.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.cave.backbase.base.interfaces.BaseListInterface
import com.cave.backbase.data.model.City
import com.cave.backbase.databinding.ItemCityListBinding
import java.text.MessageFormat

class CityListAdapter(
    val inflate: (layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> ViewBinding,
    private val listInterface: BaseListInterface<City>
) :
    com.cave.backbase.base.adapter.BaseAdapter<City>(
        inflate,
        DiffCallback
    ) {

    override fun bindView(): (City, ViewBinding, Int) -> Unit {
        return { item, viewBinding, _ ->
            val binding = viewBinding as ItemCityListBinding
            binding.tvTitle.text = MessageFormat.format("{0} - {1}", item.city, item.country)
            binding.tvLocation.text = MessageFormat.format(
                "Location is Lat = {0} - Lon = {1}",
                item.coord.lat,
                item.coord.lon
            )
            binding.root.setOnClickListener { listInterface.onItemClicked(item) }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(
            oldItem: City,
            newItem: City
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: City,
            newItem: City
        ): Boolean =
            oldItem == newItem
    }
}
