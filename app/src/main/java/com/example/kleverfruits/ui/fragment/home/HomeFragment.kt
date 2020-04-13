package com.example.kleverfruits.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.GravityCompat
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.ui.dialog.bonus.BonusDialogFragment
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : BaseFragment<HomePresenter>(), HomeView , MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        return false
    }

    override fun instantiatePresenter(): HomePresenter {
        return HomePresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var layout_festival_american_grape : RelativeLayout? = null
    var layout_flash_sale : RelativeLayout? = null
    var layout_bonus_member : RelativeLayout? = null
    var layout_new_fruits : RelativeLayout? = null
    var layout_level_gift : RelativeLayout? = null
    var layout_join_event : RelativeLayout? = null
    var layout_beauty_of_life : RelativeLayout? = null
    var layout_album_klever : RelativeLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        layout_festival_american_grape = view.findViewById(R.id.layout_festival_american_grape)
        layout_flash_sale = view.findViewById(R.id.layout_flash_sale)
        layout_bonus_member = view.findViewById(R.id.layout_bonus_member)
        layout_new_fruits = view.findViewById(R.id.layout_new_fruits)
        layout_level_gift = view.findViewById(R.id.layout_level_gift)
        layout_join_event = view.findViewById(R.id.layout_join_event)
        layout_beauty_of_life = view.findViewById(R.id.layout_beauty_of_life)
        layout_album_klever = view.findViewById(R.id.layout_album_klever)
        layout_festival_american_grape!!.setOnClickListener(View.OnClickListener {

        })
        layout_flash_sale!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(3)
        })
        layout_bonus_member!!.setOnClickListener(View.OnClickListener {
            val newFragment = BonusDialogFragment.newInstance(parentActivity!!)
            newFragment.show(parentActivity!!.supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        layout_new_fruits!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(4)
        })
        layout_level_gift!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(6)
        })
        layout_join_event!!.setOnClickListener(View.OnClickListener {

        })
        layout_beauty_of_life!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(10)
        })
        layout_album_klever!!.setOnClickListener(View.OnClickListener {

        })
        return view
    }
}