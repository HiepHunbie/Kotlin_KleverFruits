package com.example.kleverfruits.utils.`object`

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

object CommonUtil {

    fun hideKeyboard(view: View, context: Context) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun setColorImageSvg( activity: Activity,imageView: ImageView, color: Int, textView: TextView){
        DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(activity.getApplicationContext(), color));
        textView.setTextColor(activity.resources.getColor(color))
    }
    fun isValidEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

}