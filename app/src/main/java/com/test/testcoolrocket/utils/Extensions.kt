package com.test.testcoolrocket.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * This [Context] extension method can be called for showing messages.
 *
 * @param text text for showing message
 */
fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

/**
 * This [Context] extension method can be called for create [ViewModel].
 *
 * @param factory factory for creating [ViewModel]
 *
 * @return [ViewModel]
 */
inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}
