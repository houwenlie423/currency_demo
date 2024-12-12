package com.example.currency_demo.presentation.utils

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ViewExt, v 0.1 Thu 12/12/2024 7:38 PM by Houwen Lie
 */

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }
    })
}

inline fun AppCompatEditText.onTextChanged(crossinline listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           // no-op
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            listener(text?.toString().orEmpty())
        }

        override fun afterTextChanged(p0: Editable?) {
            // no-op
        }
    })
}

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}