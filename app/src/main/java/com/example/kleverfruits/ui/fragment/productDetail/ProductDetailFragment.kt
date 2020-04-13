package com.example.kleverfruits.ui.fragment.productDetail

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseFragment
import com.example.kleverfruits.model.image.ImageModel
import com.example.kleverfruits.ui.fragment.search.SearchPresenter
import com.example.kleverfruits.ui.fragment.search.SearchView
import com.example.kleverfruits.ui.main.MainActivity
import com.viewpagerindicator.CirclePageIndicator
import java.util.*

class ProductDetailFragment : BaseFragment<ProductDetailPresenter>(), ProductDetailView, MainActivity.OnBackPressedListner{
    override fun onBackPressedFragment(): Boolean {
        parentActivity!!.addFragment(parentActivity!!.tabFragment)
        return false
    }

    override fun instantiatePresenter(): ProductDetailPresenter {
        return ProductDetailPresenter(this)    }

    override fun getContext(): Context {
        return parentActivity!!.getContext()
    }

    private var imageModelArrayList: ArrayList<ImageModel>? = null

    private val myImageList = intArrayOf(R.drawable.ic_back_gift_boss, R.drawable.ic_back_gift_love, R.drawable.ic_back_gift_health, R.drawable.ic_background_flash_sale)

    private var mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    private lateinit var webView: WebView
    var img_minus : ImageView? = null
    var img_add : ImageView? = null
    var img_back : ImageView? = null
    var txt_count : TextView? = null
    var txt_real_cost : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        img_minus = view.findViewById(R.id.img_minus)
        img_add = view.findViewById(R.id.img_add)
        img_back = view.findViewById(R.id.txt_back)
        txt_count = view.findViewById(R.id.txt_number)
        txt_real_cost= view.findViewById(R.id.txt_real_cost)
        txt_real_cost!!.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        webView = view.findViewById(R.id.webview_detail)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("https://www.google.co.in/")
        webView!!.settings.javaScriptEnabled = true

        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()

        mPager = view.findViewById(R.id.pager_product_detail)
        mPager!!.adapter = SlidingImageAdapter(parentActivity!!, this.imageModelArrayList!!)

        val indicator = view.findViewById<CirclePageIndicator>(R.id.indicator_product_detail)

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.radius = 5 * density

        NUM_PAGES = imageModelArrayList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

        img_back!!.setOnClickListener(View.OnClickListener {
            parentActivity!!.addFragment(parentActivity!!.tabFragment)
        })

        checkCount()
        return view
    }

    fun checkCount(){
        var count = txt_count!!.text.toString().toInt()
        img_minus!!.setOnClickListener(View.OnClickListener {
            if(txt_count!!.text.toString().toInt()>0){
                txt_count!!.setText((txt_count!!.text.toString().toInt()-1).toString())
            }
        })
        img_add!!.setOnClickListener(View.OnClickListener {
            if(txt_count!!.text.toString().toInt()<10){
                txt_count!!.setText((txt_count!!.text.toString().toInt()+1).toString())
            }
        })
    }
    private fun populateList(): ArrayList<ImageModel> {

        val list = ArrayList<ImageModel>()

        var count = myImageList.size -1
        for (i in 0..count) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }

        return list
    }
}