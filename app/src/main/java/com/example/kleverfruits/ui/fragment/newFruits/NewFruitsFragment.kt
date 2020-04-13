package com.example.kleverfruits.ui.fragment.newFruits

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
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*

class NewFruitsFragment : BaseFragment<NewFruitsPresenter>(), NewFruitsView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(2)
        return false
    }

    override fun instantiatePresenter(): NewFruitsPresenter {
        return NewFruitsPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var listDataNewFruits : ArrayList<DataNewFruits> = ArrayList()
    var newFruitsAdapter : NewFruitsAdapter? = null
    var ryv_new_fruits: RecyclerView? = null
    var img_back_new_fruits : ImageView? = null
    var img_show_navigation_new_fruits : ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_fruits, container, false)

        ryv_new_fruits = view.findViewById(R.id.ryv_new_fruits)
        img_back_new_fruits = view.findViewById(R.id.img_back_new_fruits)
        img_show_navigation_new_fruits = view.findViewById(R.id.img_show_navigation_new_fruits)

        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))
        listDataNewFruits.add(DataNewFruits("Nho Hàn Shine Muscat","03:29:51", "1299000","1999000", ""))

        newFruitsAdapter = NewFruitsAdapter(parentActivity!!, { itemDto: DataNewFruits, position: Int ->

        })

        ryv_new_fruits!!.adapter = newFruitsAdapter
        ryv_new_fruits!!.layoutManager = GridLayoutManager(parentActivity!!, 2) as RecyclerView.LayoutManager?

        ryv_new_fruits!!.addItemDecoration(GridItemDecoration(resources.getDimensionPixelOffset(R.dimen._10sdp), 2))
        newFruitsAdapter!!.updatePosts(listDataNewFruits,false)

        ryv_new_fruits!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryv_new_fruits!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryv_new_fruits!!.addOnScrollListener(object : PaginationScrollListener((ryv_new_fruits!!.layoutManager as LinearLayoutManager?)!!) {
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

        img_back_new_fruits!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(2)
        })
        img_show_navigation_new_fruits!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return view
    }
}