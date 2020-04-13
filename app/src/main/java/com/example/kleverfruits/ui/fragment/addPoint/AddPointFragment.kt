package com.example.kleverfruits.ui.fragment.addPoint

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.ui.fragment.levelGifts.LevelGiftsPresenter
import com.example.kleverfruits.ui.fragment.levelGifts.LevelGiftsView
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class AddPointFragment : BaseFragment<AddPointPresenter>(), AddPointView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(parentActivity!!.tabFragment)
        return false
    }

    override fun instantiatePresenter(): AddPointPresenter {
        return AddPointPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var img_back_add_point : ImageView? = null
    var img_show_navigation_add_point : ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_point, container, false)

        img_back_add_point = view.findViewById(R.id.img_back_add_point)
        img_show_navigation_add_point = view.findViewById(R.id.img_show_navigation_add_point)

        img_back_add_point!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(parentActivity!!.tabFragment)
        })
        img_show_navigation_add_point!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        
        return view
    }
}