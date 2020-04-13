package com.example.kleverfruits.ui.fragment.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.ui.fragment.home.HomePresenter
import com.example.kleverfruits.ui.fragment.home.HomeView
import com.example.kleverfruits.ui.main.MainActivity

class SearchFragment : BaseFragment<SearchPresenter>(), SearchView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(0)
        return false
    }

    override fun instantiatePresenter(): SearchPresenter {
        return SearchPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var layout_flash_sale : RelativeLayout? = null
    var layout_new_fruits : RelativeLayout? = null
    var layout_level_fruits : RelativeLayout? = null
    var layout_level_gift : RelativeLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        layout_flash_sale = view.findViewById(R.id.layout_flash_sale)
        layout_new_fruits = view.findViewById(R.id.layout_new_fruits)
        layout_level_fruits = view.findViewById(R.id.layout_level_fruits)
        layout_level_gift = view.findViewById(R.id.layout_level_gift)

        layout_flash_sale!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(3)
        })
        layout_new_fruits!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(4)
        })
        layout_level_fruits!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(5)
        })
        layout_level_gift!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(6)
        })

        return view
    }
}