package com.example.kleverfruits.ui.fragment.levelGifts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.model.newFruits.DataNewFruits
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsAdapter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsPresenter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsView
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*

class LevelGiftsFragment : BaseFragment<LevelGiftsPresenter>(), LevelGiftsView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(2)
        return false
    }

    override fun instantiatePresenter(): LevelGiftsPresenter {
        return LevelGiftsPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var img_back_level_gifts : ImageView? = null
    var img_show_navigation_level_gifts : ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_level_gift, container, false)

        img_back_level_gifts = view.findViewById(R.id.img_back_level_gifts)
        img_show_navigation_level_gifts = view.findViewById(R.id.img_show_navigation_level_gifts)

        img_back_level_gifts!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(2)
        })
        img_show_navigation_level_gifts!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return view
    }
}