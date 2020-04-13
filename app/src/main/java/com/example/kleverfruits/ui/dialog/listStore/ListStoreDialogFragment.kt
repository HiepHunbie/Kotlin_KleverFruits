package com.example.kleverfruits.ui.dialog.listStore

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.model.listStore.DataListStore
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.ClickListener
import com.example.kleverfruits.utils.PaginationScrollListener
import com.example.kleverfruits.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_store.view.*

class ListStoreDialogFragment : BaseFragment<ListStorePresenter>(),ListStoreView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(0)
        return false
    }

    override fun instantiatePresenter(): ListStorePresenter {
        return ListStorePresenter(this)
    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

//    var activity : MainActivity? = null
    private var listStoreAdapter: ListStoreAdapter? = null
    var listDevices = ArrayList<DataListStore>()
    var ryvListStore : RecyclerView? = null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        this.activity = arguments!!.getSerializable("activity") as MainActivity?
//        // Pick a style based on the num.
//        val style = DialogFragment.STYLE_NO_FRAME
//        val theme = R.style.DialogCustom
//        setStyle(style, theme)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_store, container, false)

        ryvListStore = rootView.findViewById(R.id.ryv_list_store)
        listStoreAdapter = ListStoreAdapter(parentActivity!!,{ itemDto: DataListStore, position: Int ->

        })

        listDevices.add(DataListStore("DONG DA","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("THANH XUAN","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listDevices.add(DataListStore("","khuong thuong, hn","09999999"))
        listStoreAdapter!!.updatePosts(listDevices,false)

        ryvListStore!!.layoutManager = LinearLayoutManager(parentActivity, LinearLayoutManager.VERTICAL!!, false)
        ryvListStore!!.adapter = listStoreAdapter
        ryvListStore!!.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!.applicationContext,
                ryvListStore!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )
        ryvListStore!!.addOnScrollListener(object : PaginationScrollListener((ryvListStore!!.layoutManager as LinearLayoutManager?)!!) {
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

        rootView.layout_region_hn.setOnClickListener(View.OnClickListener {
            rootView.txt_title_region_hanoi.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_number_store_in_hn.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_title_region_hcm.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_number_store_in_hcm.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
        })
        rootView.layout_region_hcm.setOnClickListener(View.OnClickListener {
            rootView.txt_title_region_hcm.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_number_store_in_hcm.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_title_region_hanoi.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_number_store_in_hn.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
        })
        rootView.img_back_list_store.setOnClickListener {
            parentActivity!!.addFragment(0)
        }
        rootView.img_show_navigation_list_store.setOnClickListener {
            if(parentActivity!!.drawer_layout.isDrawerOpen(GravityCompat.START)){
                parentActivity!!.drawer_layout.closeDrawer(GravityCompat.START)
            }else{
                parentActivity!!.drawer_layout.openDrawer(GravityCompat.START)
            }
        }
//        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_BACK){
//                dialog.dismiss()
//            }
//            false
//        })
        return rootView
    }


//    companion object {
//        fun newInstance(activity: MainActivity): ListStoreDialogFragment {
//            val f = ListStoreDialogFragment()
//
//            // Supply num input as an argument.
//            val args = Bundle()
//            args.putSerializable("activity", activity)
//            f.arguments = args
//
//            return f
//        }
//    }
}