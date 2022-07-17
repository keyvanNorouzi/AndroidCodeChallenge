package com.cave.backbase.ui

import android.view.LayoutInflater
import com.cave.backbase.base.BaseActivity
import com.cave.backbase.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun getViewModelJavaClass(): KClass<MainViewModel> = MainViewModel::class
}
