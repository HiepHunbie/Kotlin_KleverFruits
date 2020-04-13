package com.example.kleverfruits.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import java.io.Serializable

abstract class BaseActivity<P : com.example.kleverfruits.base.BasePresenter<com.example.kleverfruits.base.BaseView>> : com.example.kleverfruits.base.BaseView, AppCompatActivity() {
    lateinit var presenter: P
    var prefs: SharedPreferences? = null
    private val PREFS_FILENAME ="com.example.shop49k"
    var gson = GsonBuilder().create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT >= 16) {
//            getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
//            getWindow().getDecorView().setSystemUiVisibility(3328);
//        }else{
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
        presenter = instantiatePresenter()
        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context {
        return this
    }

}