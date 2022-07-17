package com.cave.backbase.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

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
