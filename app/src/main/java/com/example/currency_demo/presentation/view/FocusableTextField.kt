package com.example.currency_demo.presentation.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.currency_demo.presentation.utils.hideKeyboard


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version CustomTextField, v 0.1 Sat 12/14/2024 1:12 AM by Houwen Lie
 */
class FocusableTextField (context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (!focused) hideKeyboard()
    }
}