package com.cave.backbase.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    abstract fun getViewModelJavaClass(): KClass<VM>

    val viewModel: VM by stateViewModel(clazz = getViewModelJavaClass())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        // unbind XML
        _binding = null
    }

    fun showSnackBarMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
