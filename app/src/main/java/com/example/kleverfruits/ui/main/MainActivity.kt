package com.example.kleverfruits.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseActivity
import com.example.kleverfruits.ui.dialog.bonus.BonusDialogFragment
import com.example.kleverfruits.ui.dialog.cart.CartDialogFragment
import com.example.kleverfruits.ui.dialog.listStore.ListStoreDialogFragment
import com.example.kleverfruits.ui.dialog.login.LoginDialogFragment
import com.example.kleverfruits.ui.dialog.memberInfo.MemberInfoDialogFragment
import com.example.kleverfruits.ui.dialog.order.OrderDialogFragment
import com.example.kleverfruits.ui.dialog.signup.SignUpDialogFragment
import com.example.kleverfruits.ui.fragment.addPoint.AddPointFragment
import com.example.kleverfruits.ui.fragment.beautyOfLife.BeautyOfLifeFragment
import com.example.kleverfruits.ui.fragment.flashSale.FlashSaleFragment
import com.example.kleverfruits.ui.fragment.home.HomeFragment
import com.example.kleverfruits.ui.fragment.levelFruits.LevelFruitsFragment
import com.example.kleverfruits.ui.fragment.levelGifts.LevelGiftsFragment
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsFragment
import com.example.kleverfruits.ui.fragment.notification.NotificationFragment
import com.example.kleverfruits.ui.fragment.productDetail.ProductDetailFragment
import com.example.kleverfruits.ui.fragment.sale.SaleFragment
import com.example.kleverfruits.ui.fragment.search.SearchFragment
import com.example.kleverfruits.ui.smallActivity.infoAccount.InfoAccountActivity
import com.example.kleverfruits.utils.LOGIN_STATUS
import com.example.kleverfruits.utils.`object`.CommonUtil
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*
import java.io.Serializable

class MainActivity : BaseActivity<MainPresenter>(),MainView, Serializable, NavigationView.OnNavigationItemSelectedListener  {
    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    var headerView : View? = null
    var tabFragment = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setNavigationItemSelectedListener(this)

        button_home.setOnClickListener(View.OnClickListener {

            addFragment(0)
        })
        button_search.setOnClickListener(View.OnClickListener {

            addFragment(2)
        })
        button_add_point.setOnClickListener(View.OnClickListener {

            addFragment(7)
        })
        button_notification.setOnClickListener(View.OnClickListener {
            addFragment(9)
        })
        button_cart.setOnClickListener(View.OnClickListener {
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.button_main_hide, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.background_main, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.button_main_hide, txt_button_add_point)
            val newFragment = CartDialogFragment.newInstance(this)
            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })

        img_show_navigation.setOnClickListener(View.OnClickListener {
            if(drawer_layout.isDrawerOpen(GravityCompat.START)){
                showNavigation(false)
            }else{
                showNavigation(true)
            }
//            drawer_layout.openDrawer(GravityCompat.START)
        })
        button_home.callOnClick()
        drawer_layout.closeDrawer(GravityCompat.START)

         headerView = nav_view.getHeaderView(0)

        headerView!!.img_close_navigation.setOnClickListener(View.OnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.layout_shop_offices.setOnClickListener(View.OnClickListener {
//            val newFragment = ListStoreDialogFragment.newInstance(this)
//            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
            addFragment(1)
        })
        headerView!!.layout_level_fruits.setOnClickListener(View.OnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            addFragment(5)
        })
        headerView!!.layout_level_gift.setOnClickListener(View.OnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            addFragment(6)
        })
        headerView!!.layout_sale.setOnClickListener(View.OnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            addFragment(11)
        })
        headerView!!.layout_new_order.setOnClickListener(View.OnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            addFragment(4)
        })
        headerView!!.layout_sale_product.setOnClickListener(View.OnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            addFragment(3)
        })
        headerView!!.txt_login.setOnClickListener(View.OnClickListener {
            val newFragment = LoginDialogFragment.newInstance(this)
            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.txt_signup.setOnClickListener(View.OnClickListener {
            val newFragment = SignUpDialogFragment.newInstance(this)
            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.layout_member.setOnClickListener(View.OnClickListener {
            val newFragment = MemberInfoDialogFragment.newInstance(this)
            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.img_avatar.setOnClickListener(View.OnClickListener {
            val nextScreenIntent = Intent(this, InfoAccountActivity::class.java)
            startActivity(nextScreenIntent)
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.layout_manage_order.setOnClickListener(View.OnClickListener {
            val newFragment = OrderDialogFragment.newInstance(this)
            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.layout_bonus_member.setOnClickListener(View.OnClickListener {
            val newFragment = BonusDialogFragment.newInstance(this)
            newFragment.show(supportFragmentManager,"")
            drawer_layout.closeDrawer(GravityCompat.START)
        })
        headerView!!.layout_beauty_of_life.setOnClickListener(View.OnClickListener {
            addFragment(10)
            drawer_layout.closeDrawer(GravityCompat.START)
        })

    }

    fun showNavigation(isShow : Boolean){
        if(isShow){
            drawer_layout.openDrawer(GravityCompat.START)
        }else{
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }
    interface OnBackPressedListner {
        fun onBackPressedFragment(): Boolean
    }
    override fun addFragment(pos:Int) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        if(pos != 7 && pos != 8 ){
            tabFragment = pos
        }
        if(pos ==0){
            ft.replace(R.id.container_frame, HomeFragment())
            img_show_navigation.visibility = View.VISIBLE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.background_main, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.button_main_hide, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==1){
            ft.replace(R.id.container_frame, ListStoreDialogFragment())
            img_show_navigation.visibility = View.GONE
        }else if(pos ==2){
            ft.replace(R.id.container_frame, SearchFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==3){
            ft.replace(R.id.container_frame, FlashSaleFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==4){
            ft.replace(R.id.container_frame, NewFruitsFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==5){
            ft.replace(R.id.container_frame, LevelFruitsFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==6){
            ft.replace(R.id.container_frame, LevelGiftsFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==7){
            ft.replace(R.id.container_frame, AddPointFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.button_main_hide, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            txt_button_add_point.setTextColor(resources.getColor(R.color.background_main))
        }else if(pos ==8){
            ft.replace(R.id.container_frame, ProductDetailFragment())
            img_show_navigation.visibility = View.GONE
        }else if(pos ==9){
            ft.replace(R.id.container_frame, NotificationFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.button_main_hide, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.background_main, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==10){
            ft.replace(R.id.container_frame, BeautyOfLifeFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }else if(pos ==11){
            ft.replace(R.id.container_frame, SaleFragment())
            img_show_navigation.visibility = View.GONE
            CommonUtil.setColorImageSvg(this,img_button_home, R.color.button_main_hide, txt_button_home)
            CommonUtil.setColorImageSvg(this,img_button_search, R.color.background_main, txt_button_search)
            CommonUtil.setColorImageSvg(this,img_button_notificaiton, R.color.button_main_hide, txt_button_notification)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_cart)
            CommonUtil.setColorImageSvg(this,img_button_cart, R.color.button_main_hide, txt_button_add_point)
        }
        ft.commitAllowingStateLoss()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        val fragment = supportFragmentManager.findFragmentById(R.id.container_frame)
        if (!(fragment is OnBackPressedListner) || !(fragment as OnBackPressedListner).onBackPressedFragment())
        {
//            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun checkStatus(){
        var status = prefs!!.getInt(LOGIN_STATUS, 0)
        if(headerView!!.layout_view_account != null){
            if(status == 0){
                headerView!!.layout_view_account.visibility = View.GONE
                headerView!!.layout_login_account.visibility = View.VISIBLE
            }else{
                headerView!!.layout_view_account.visibility = View.VISIBLE
                headerView!!.layout_login_account.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var status = prefs!!.getInt(LOGIN_STATUS, 0)
        if(headerView!!.layout_view_account != null){
            if(status == 0){
                headerView!!.layout_view_account.visibility = View.GONE
                headerView!!.layout_login_account.visibility = View.VISIBLE
            }else{
                headerView!!.layout_view_account.visibility = View.VISIBLE
                headerView!!.layout_login_account.visibility = View.GONE
            }
        }

    }
}