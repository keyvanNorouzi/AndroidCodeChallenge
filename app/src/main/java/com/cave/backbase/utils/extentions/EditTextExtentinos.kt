package com.cave.backbase.utils.extentions

import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@OptIn(ExperimentalCoroutinesApi::class)
fun EditText.textInputAsFlow() = callbackFlow {
    val watcher: TextWatcher = doOnTextChanged { textInput: CharSequence?, _, _, _ ->
        offer(textInput)
    }
    awaitClose { this@textInputAsFlow.removeTextChangedListener(watcher) }
}
