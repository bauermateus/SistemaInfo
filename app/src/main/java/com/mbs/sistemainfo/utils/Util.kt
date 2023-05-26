package com.mbs.sistemainfo.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(context: Context, view: View) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String.formatCpf(): String {
    return "${this.substring(0, 3)}.${this.substring(3, 6)}.${this.substring(6, 9)}-${this.substring(9, 11)}"
}
