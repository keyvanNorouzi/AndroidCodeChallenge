package com.cave.backbase.utils.extentions

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    if (!isEnabled) {
        isEnabled = true
    }
}

fun View.hideKeyboard() {
    val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
