package com.cave.backbase.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.cave.backbase.base.activity.BaseActivity
import com.cave.backbase.databinding.ActivityMainBinding
import com.cave.backbase.ui.list.ListFragment
import com.cave.backbase.utils.extentions.showFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if ((savedInstanceState?.getInt("rotated", 0) ?: 0) == 0) {
            navigateToListFragment()
        }
    }

    private fun navigateToListFragment() {
        showFragment(ListFragment.newInstance())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("rotated", 1)
    }
}
