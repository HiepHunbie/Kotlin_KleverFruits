package com.example.kleverfruits.ui.fragment.notification

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
import com.example.kleverfruits.model.notification.DataNotification
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsAdapter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsPresenter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsView
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import com.example.kleverfruits.utils.`object`.NotificationUtil
import kotlinx.android.synthetic.main.activity_main.*

class NotificationFragment : BaseFragment<NotificationPresenter>(), NotificationView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(0)
        return false
    }

    override fun instantiatePresenter(): NotificationPresenter {
        return NotificationPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var listDataNotification : ArrayList<DataNotification> = ArrayList()
    var notificationAdapter : NotificationAdapter? = null
    var ryv_notification: RecyclerView? = null
    var img_show_navigation_notification : ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        ryv_notification = view.findViewById(R.id.ryv_notification)
        img_show_navigation_notification = view.findViewById(R.id.img_show_navigation_notification)

        listDataNotification.add(DataNotification("",1,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",0,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",0,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",1,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",1,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",1,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",1,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))
        listDataNotification.add(DataNotification("",1,"Làm gì cũng được, cứ giao dịch là có quà",
            "Không cần suy nghĩ, cứ giao dịch qua app Klever Fruits sẽ nhận được quà Cơ hội nhận iPhone 11 Pro max, vali mạ vàng, tai nghe Airpods...",
            "11/11/2019","https://www.google.co.in/"))

        notificationAdapter = NotificationAdapter(parentActivity!!, { itemDto: DataNotification, position: Int ->
            NotificationUtil.showDialogNotificationDetail(parentActivity!!,itemDto)
        })

        ryv_notification!!.adapter = notificationAdapter
        ryv_notification!!.layoutManager = LinearLayoutManager(parentActivity, LinearLayoutManager.VERTICAL!!, false)

//        ryv_notification!!.addItemDecoration(GridItemDecoration(resources.getDimensionPixelOffset(R.dimen._10sdp), 2))
        notificationAdapter!!.updatePosts(listDataNotification,false)

        ryv_notification!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryv_notification!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryv_notification!!.addOnScrollListener(object : PaginationScrollListener((ryv_notification!!.layoutManager as LinearLayoutManager?)!!) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
//            pages+=1
//            presenter!!.getDevices(token,size,pages,sort,order,searchValue,progressBarLoadMore!!,true,type,locId,parentActivity!!.orgId,parentActivity!!,isFirst)
            }
        })

        img_show_navigation_notification!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return view
    }
}