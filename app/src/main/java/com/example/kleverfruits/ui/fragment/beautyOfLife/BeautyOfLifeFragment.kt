package com.example.kleverfruits.ui.fragment.beautyOfLife

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.model.beautyOfLife.DataBeautyOfLife
import com.example.kleverfruits.model.beautyOfLife.DataBeautyOfLifeDetail
import com.example.kleverfruits.model.flashSale.DataFlashSale
import com.example.kleverfruits.ui.fragment.flashSale.FlashSaleAdapter
import com.example.kleverfruits.ui.fragment.flashSale.FlashSalePresenter
import com.example.kleverfruits.ui.fragment.flashSale.FlashSaleView
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import com.example.kleverfruits.utils.`object`.BeautyOfLifeUtil
import com.example.kleverfruits.utils.`object`.BonusUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_order.view.*

class BeautyOfLifeFragment : BaseFragment<BeautyOfLifePresenter>(), BeautyOfLifeView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(2)
        return false
    }

    override fun instantiatePresenter(): BeautyOfLifePresenter {
        return BeautyOfLifePresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var listDataBeautyOfLife : ArrayList<DataBeautyOfLife> = ArrayList()
    var beautyOfLifeAdapter : BeautyOfLifeAdapter? = null
    var beautyOfLifeSmallAdapter : BeautyOfLifeSmallAdapter? = null
    var ryv_beauty_of_life: RecyclerView? = null
    var ryv_beauty_of_life_small: RecyclerView? = null
    var img_back_beauty_of_life : ImageView? = null
    var img_show_navigation_beauty_of_life : ImageView? = null
    var txt_title : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_beauty_of_life, container, false)

        ryv_beauty_of_life = view.findViewById(R.id.ryv_beauty_of_life)
        ryv_beauty_of_life_small = view.findViewById(R.id.ryv_beauty_of_life_small)
        img_back_beauty_of_life = view.findViewById(R.id.img_back_beauty_of_life)
        img_show_navigation_beauty_of_life = view.findViewById(R.id.img_show_navigation_beauty_of_life)
        txt_title = view.findViewById(R.id.txt_title)

        listDataBeautyOfLife.add(DataBeautyOfLife("Thoi trang", "", listOf(DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"))))
        listDataBeautyOfLife.add(DataBeautyOfLife("Am thuc", "", listOf(DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"))))
        listDataBeautyOfLife.add(DataBeautyOfLife("The thao", "", listOf(DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"))))
        listDataBeautyOfLife.add(DataBeautyOfLife("Am nhac", "", listOf(DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"))))
        listDataBeautyOfLife.add(DataBeautyOfLife("Xa hoi", "", listOf(DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"),DataBeautyOfLifeDetail("","Trang phục gợi cảm trên thảm đỏ 'Ngôi sao của năm 2020'",
            "17/01/2020","https://www.google.co.in/"))))

        beautyOfLifeSmallAdapter = BeautyOfLifeSmallAdapter(parentActivity!!, { itemDto: DataBeautyOfLifeDetail, position: Int ->
            BeautyOfLifeUtil.showDialogBeautyOfLifeDetail(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
            })
        })
        ryv_beauty_of_life_small!!.adapter = beautyOfLifeSmallAdapter
        ryv_beauty_of_life_small!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
        beautyOfLifeAdapter = BeautyOfLifeAdapter(parentActivity!!, { itemDto: DataBeautyOfLife, position: Int ->
            beautyOfLifeSmallAdapter!!.updatePosts(itemDto.lisDataBeautyOfLife,false)
            ryv_beauty_of_life_small!!.visibility = View.VISIBLE
            txt_title!!.setText(itemDto.title)
        })

        ryv_beauty_of_life!!.adapter = beautyOfLifeAdapter
        ryv_beauty_of_life!!.layoutManager = GridLayoutManager(parentActivity!!, 2) as RecyclerView.LayoutManager?

        ryv_beauty_of_life!!.addItemDecoration(GridItemDecoration(resources.getDimensionPixelOffset(R.dimen._10sdp), 2))
        beautyOfLifeAdapter!!.updatePosts(listDataBeautyOfLife,false)

        ryv_beauty_of_life!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryv_beauty_of_life!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryv_beauty_of_life!!.addOnScrollListener(object : PaginationScrollListener((ryv_beauty_of_life!!.layoutManager as LinearLayoutManager?)!!) {
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

        img_back_beauty_of_life!!.setOnClickListener(View.OnClickListener {
            if(ryv_beauty_of_life_small!!.visibility == View.VISIBLE){
                ryv_beauty_of_life_small!!.visibility = View.GONE
                txt_title!!.setText(parentActivity!!.getString(R.string.beauty_of_life))
            }else{
                parentActivity!!.addFragment(2)
            }
        })
        img_show_navigation_beauty_of_life!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return view
    }
}