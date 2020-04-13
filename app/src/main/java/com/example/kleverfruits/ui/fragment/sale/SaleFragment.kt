package com.example.kleverfruits.ui.fragment.sale

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.model.newFruits.DataNewFruits
import com.example.kleverfruits.model.sale.DataSale
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsAdapter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsPresenter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsView
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import com.example.kleverfruits.utils.`object`.SaleUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_klever_bonus.view.*

class SaleFragment  : BaseFragment<SalePresenter>(), SaleView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(2)
        return false
    }

    override fun instantiatePresenter(): SalePresenter {
        return SalePresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    fun getListSale(status : Int): ArrayList<DataSale>{
        var list : ArrayList<DataSale> = ArrayList()
        for (item in listDataSale){
            if(item.status==status){
                list.add(item)
            }
        }
        return list
    }
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var listDataSale : ArrayList<DataSale> = ArrayList()
    var saleAdapter : SaleAdapter? = null
    var ryv_sale: RecyclerView? = null
    var img_back_sale : ImageView? = null
    var img_show_navigation_sale : ImageView? = null
    var layout_happenning : RelativeLayout? = null
    var layout_upcoming : RelativeLayout? = null
    var layout_happenned : RelativeLayout? = null
    var txt_happenning : TextView? = null
    var txt_upcoming : TextView? = null
    var txt_happenned : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sale, container, false)

        ryv_sale = view.findViewById(R.id.ryv_sale)
        img_back_sale = view.findViewById(R.id.img_back_sale)
        img_show_navigation_sale = view.findViewById(R.id.img_show_navigation_sale)
        layout_happenning = view.findViewById(R.id.layout_happenning)
        layout_upcoming = view.findViewById(R.id.layout_upcoming)
        layout_happenned = view.findViewById(R.id.layout_happenned)
        txt_happenning = view.findViewById(R.id.txt_happenning)
        txt_upcoming = view.findViewById(R.id.txt_upcoming)
        txt_happenned = view.findViewById(R.id.txt_happenned)

        layout_happenning!!.setOnClickListener(View.OnClickListener {
            txt_happenning!!.setTextColor(activity!!.resources.getColor(R.color.background_main))
            txt_upcoming!!.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            txt_happenned!!.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            layout_happenning!!.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            layout_upcoming!!.setBackgroundResource(R.drawable.custom_border_bottom_white)
            layout_happenned!!.setBackgroundResource(R.drawable.custom_border_bottom_white)
            saleAdapter!!.updatePosts(getListSale(0),false)
        })
        layout_upcoming!!.setOnClickListener(View.OnClickListener {
            txt_happenning!!.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            txt_upcoming!!.setTextColor(activity!!.resources.getColor(R.color.background_main))
            txt_happenned!!.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            layout_happenning!!.setBackgroundResource(R.drawable.custom_border_bottom_white)
            layout_upcoming!!.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            layout_happenned!!.setBackgroundResource(R.drawable.custom_border_bottom_white)
            saleAdapter!!.updatePosts(getListSale(1),false)
        })
        layout_happenned!!.setOnClickListener(View.OnClickListener {
            txt_happenning!!.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            txt_upcoming!!.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            txt_happenned!!.setTextColor(activity!!.resources.getColor(R.color.background_main))
            layout_happenning!!.setBackgroundResource(R.drawable.custom_border_bottom_white)
            layout_upcoming!!.setBackgroundResource(R.drawable.custom_border_bottom_white)
            layout_happenned!!.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            saleAdapter!!.updatePosts(getListSale(2),false)
        })

        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",0,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",1,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",1,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",2,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",0,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",2,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",1,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",2,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",0,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",0,"https://www.google.co.in/"))
        listDataSale.add(DataSale("","Chương trình sale tháng 10/2020 với rất nhiều ưu đãi lớn đến từ Klever Fruits","17/01/2020",2,"https://www.google.co.in/"))
        saleAdapter = SaleAdapter(parentActivity!!, { itemDto: DataSale, position: Int ->
            SaleUtil.showDialogBeautyOfLifeDetail(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
            })
        })

        ryv_sale!!.adapter = saleAdapter
        ryv_sale!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)

        ryv_sale!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryv_sale!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryv_sale!!.addOnScrollListener(object : PaginationScrollListener((ryv_sale!!.layoutManager as LinearLayoutManager?)!!) {
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

        layout_happenning!!.callOnClick()
        img_back_sale!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(2)
        })
        img_show_navigation_sale!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return view
    }
}