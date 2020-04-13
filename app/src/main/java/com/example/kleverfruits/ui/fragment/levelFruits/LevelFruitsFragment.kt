package com.example.kleverfruits.ui.fragment.levelFruits

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.model.levelFruits.DataFilterLevelFruits
import com.example.kleverfruits.model.levelFruits.DataLevelFruits
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.GridItemDecoration
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_level_fruits.*

class LevelFruitsFragment : BaseFragment<LevelFruitsPresenter>(), LevelFruitsView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(2)
        return false
    }

    override fun instantiatePresenter(): LevelFruitsPresenter {
        return LevelFruitsPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var listDataLevelFruits : ArrayList<DataLevelFruits> = ArrayList()
    var levelFruitsAdapter : LevelFruitsAdapter? = null
    var listDataFilterLevelFruits : ArrayList<DataFilterLevelFruits> = ArrayList()
    var lilterLevelFruitsAdapter : FilterLevelFruitsAdapter? = null
    var ryv_level_fruits: RecyclerView? = null
    var ryv_filter_level_fruits : RecyclerView? = null
    var img_back_level_fruits : ImageView? = null
    var img_show_navigation_level_fruits : ImageView? = null
    var txt_filter : TextView? = null
    var img_filter_level_fruits: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_level_fruits, container, false)

        txt_filter = view.findViewById(R.id.txt_filter)
        img_filter_level_fruits = view.findViewById(R.id.img_filter_level_fruits)
        ryv_level_fruits = view.findViewById(R.id.ryv_level_fruits)
        img_back_level_fruits = view.findViewById(R.id.img_back_level_fruits)
        img_show_navigation_level_fruits = view.findViewById(R.id.img_show_navigation_level_fruits)
        ryv_filter_level_fruits = view.findViewById(R.id.ryv_filter_level_fruits)

        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Mỹ", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Hàn Quốc", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Mỹ", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Mỹ", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Canada", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Việt Nam", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Việt Nam", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Canada", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Việt Nam", "1299000","1999000", ""))
        listDataLevelFruits.add(DataLevelFruits("Nho Hàn Shine Muscat","Trái cây Úc", "1299000","1999000", ""))

        listDataFilterLevelFruits.add(DataFilterLevelFruits("Tất cả", "0"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits("Trái cây Mỹ", "1"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits("Trái cây Úc", "2"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits("Trái cây Hàn Quốc", "3"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits("Trái cây Canada", "4"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits("Trái cây Pháp", "5"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits("Trái cây Việt Nam", "6"))


        levelFruitsAdapter = LevelFruitsAdapter(parentActivity!!, { itemDto: DataLevelFruits, position: Int ->
            parentActivity!!.addFragment(8)
        })

        lilterLevelFruitsAdapter = FilterLevelFruitsAdapter(parentActivity!!, { itemDto: DataFilterLevelFruits, position: Int ->
            ryv_filter_level_fruits!!.visibility = View.GONE
        })

        ryv_filter_level_fruits!!.adapter = lilterLevelFruitsAdapter
        ryv_filter_level_fruits!!.layoutManager = LinearLayoutManager(parentActivity, LinearLayoutManager.VERTICAL!!, false)
        lilterLevelFruitsAdapter!!.updatePosts(listDataFilterLevelFruits,"0",false)

        ryv_level_fruits!!.adapter = levelFruitsAdapter
        ryv_level_fruits!!.layoutManager = GridLayoutManager(parentActivity!!, 2) as RecyclerView.LayoutManager?

        ryv_level_fruits!!.addItemDecoration(GridItemDecoration(resources.getDimensionPixelOffset(R.dimen._10sdp), 2))
        levelFruitsAdapter!!.updatePosts(listDataLevelFruits,false)

        ryv_level_fruits!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryv_level_fruits!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryv_level_fruits!!.addOnScrollListener(object : PaginationScrollListener((ryv_level_fruits!!.layoutManager as LinearLayoutManager?)!!) {
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

        img_back_level_fruits!!.setOnClickListener(View.OnClickListener {
            if(ryv_filter_level_fruits!!.visibility == View.VISIBLE){
                ryv_filter_level_fruits!!.visibility = View.GONE
            }else{
                parentActivity!!.addFragment(2)
            }
        })
        img_show_navigation_level_fruits!!.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        img_filter_level_fruits!!.setOnClickListener(View.OnClickListener {
            if(ryv_filter_level_fruits!!.visibility == View.GONE){
                ryv_filter_level_fruits!!.visibility = View.VISIBLE
            }
        })
        return view
    }
}