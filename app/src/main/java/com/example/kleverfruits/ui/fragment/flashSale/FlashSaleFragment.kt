package com.example.kleverfruits.ui.fragment.flashSale

import android.app.Dialog
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
import com.example.kleverfruits.model.flashSale.DataFlashSale
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_flashsale.view.*
import kotlinx.android.synthetic.main.fragment_list_store.view.*

class FlashSaleFragment : BaseFragment<FlashSalePresenter>(), FlashSaleView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(2)
        return false
    }

    override fun instantiatePresenter(): FlashSalePresenter {
        return FlashSalePresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var listDataFlashSale : ArrayList<DataFlashSale> = ArrayList()
    var flashSaleAdapter : FlashSaleAdapter? = null
    var ryv_flash_sale:RecyclerView? = null
    var img_back_flash_sale : ImageView ? = null
    var img_show_navigation_flash_sale : ImageView ? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flashsale, container, false)

        ryv_flash_sale = view.findViewById(R.id.ryv_flash_sale)
        img_back_flash_sale = view.findViewById(R.id.img_back_flash_sale)
        img_show_navigation_flash_sale = view.findViewById(R.id.img_show_navigation_flash_sale)

        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataFlashSale.add(DataFlashSale("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))

        flashSaleAdapter = FlashSaleAdapter(parentActivity!!, { itemDto: DataFlashSale, position: Int ->

        })

        ryv_flash_sale!!.adapter = flashSaleAdapter
        ryv_flash_sale!!.layoutManager = GridLayoutManager(parentActivity!!, 2) as RecyclerView.LayoutManager?

        ryv_flash_sale!!.addItemDecoration(GridItemDecoration(resources.getDimensionPixelOffset(R.dimen._10sdp), 2))
        flashSaleAdapter!!.updatePosts(listDataFlashSale,false)

        ryv_flash_sale!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryv_flash_sale!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryv_flash_sale!!.addOnScrollListener(object : PaginationScrollListener((ryv_flash_sale!!.layoutManager as LinearLayoutManager?)!!) {
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

        img_back_flash_sale!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(2)
        })
        img_show_navigation_flash_sale!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return view
    }
}