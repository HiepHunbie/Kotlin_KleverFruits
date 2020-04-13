package com.example.kleverfruits.ui.dialog.memberInfo

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.model.bonus.DataBonus
import com.example.kleverfruits.model.image.ImageModel
import com.example.kleverfruits.model.memberInfo.MemberInfoBonusData
import com.example.kleverfruits.ui.dialog.bonus.*
import com.example.kleverfruits.ui.fragment.productDetail.SlidingImageAdapter
import com.example.kleverfruits.ui.main.MainActivity
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.fragment_member_info.view.*
import java.util.*
import kotlin.collections.ArrayList

class MemberInfoDialogFragment : BaseDialogFragment<MemberInfoPresenter>(), MemberInfoView {
    override fun instantiatePresenter(): MemberInfoPresenter {
        return MemberInfoPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null
    private var imageModelArrayList: ArrayList<ImageModel>? = null

    private val myImageList = intArrayOf(R.drawable.ic_momo_bonus, R.drawable.ic_momo_bonus, R.drawable.ic_momo_bonus, R.drawable.ic_momo_bonus)

    private var mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    private var memberInfoAdapter: MemberInfoAdapter? = null
    var listMemberInfoBonusData : ArrayList<MemberInfoBonusData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_member_info, container, false)

        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()

        mPager = rootView.findViewById(R.id.pager_bonus_detail)
        mPager!!.adapter = SlidingImageMemberAdapter(parentActivity!!, this.imageModelArrayList!!)

        val indicator = rootView.findViewById<CirclePageIndicator>(R.id.indicator_bonus_detail)

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

        memberInfoAdapter = MemberInfoAdapter(activity!!)
        rootView?.ryv_member!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
        rootView.ryv_member!!.adapter = memberInfoAdapter
        rootView.layout_member.setOnClickListener(View.OnClickListener {
            rootView.layout_member.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_sliver_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_gold_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_diamound_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.txt_title_member.setText(activity!!.getString(R.string.klever_fruits_member))
            memberInfoAdapter!!.updatePosts(getListMemberInfoBonusData(0),false)
            rootView.prb_member.progressDrawable = activity!!.resources.getDrawable(R.drawable.horizontal_progressbar_custom)
        })
        rootView.layout_sliver_member.setOnClickListener(View.OnClickListener {
            rootView.layout_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_sliver_member.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_gold_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_diamound_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.txt_title_member.setText(activity!!.getString(R.string.sliver_bonus))
            memberInfoAdapter!!.updatePosts(getListMemberInfoBonusData(1),false)
            rootView.prb_member.progressDrawable = activity!!.resources.getDrawable(R.drawable.horizontal_progressbar_custom_green)
        })
        rootView.layout_gold_member.setOnClickListener(View.OnClickListener {
            rootView.layout_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_sliver_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_gold_member.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_diamound_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.txt_title_member.setText(activity!!.getString(R.string.gold_bonus))
            memberInfoAdapter!!.updatePosts(getListMemberInfoBonusData(2),false)
            rootView.prb_member.progressDrawable = activity!!.resources.getDrawable(R.drawable.horizontal_progressbar_custom_yellow)
        })
        rootView.layout_diamound_member.setOnClickListener(View.OnClickListener {
            rootView.layout_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_sliver_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_gold_member.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_diamound_member.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.txt_title_member.setText(activity!!.getString(R.string.diamound_bonus))
            memberInfoAdapter!!.updatePosts(getListMemberInfoBonusData(3),false)
            rootView.prb_member.progressDrawable = activity!!.resources.getDrawable(R.drawable.horizontal_progressbar_custom_blue)
        })
        rootView.layout_member.callOnClick()

        rootView.layout_detail_bonus.setOnClickListener(View.OnClickListener {
            val newFragment = BonusDialogFragment.newInstance(activity!!)
            newFragment.show(activity!!.supportFragmentManager,"")
        })
        rootView.layout_view_details.setOnClickListener(View.OnClickListener {
            val newFragment = BonusDialogFragment.newInstance(activity!!)
            newFragment.show(activity!!.supportFragmentManager,"")
        })
        rootView.ic_back_member_info.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
//                dialog.dismiss()
            }
            false
        })
        return rootView
    }

    companion object {
        fun newInstance(activity: MainActivity): MemberInfoDialogFragment {
            val f = MemberInfoDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            f.arguments = args

            return f
        }
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

    private fun getListMemberInfoBonusData(type : Int): ArrayList<MemberInfoBonusData>{
        var listData : ArrayList<MemberInfoBonusData> = ArrayList()
        listData.add(MemberInfoBonusData(0,"Chào mừng bạn đến với Klever Fruits! Với mỗi đơn hàng thanh toán trên ứng dụng Klever Fruits bạn sẽ được tích lũy một số điểm tương ứng. Điểm số càng cao, bạn sẽ có cơ hội nâng hạng và kèm theo đó là những đặc quyền hấp dẫn"))
        listData.add(MemberInfoBonusData(1,"Đổi phần thưởng với số điểm ít hơn. Bạn sẽ được tận hưởng nhiều ưu đãi hơn khi nâng cấp lên các hạng mức cao hơn"))
        listData.add(MemberInfoBonusData(1,"Ưu đãi từ đối tác của Klever Fruits"))
        listData.add(MemberInfoBonusData(2,"Nhân 1,5 lần điểm thưởng"))
        listData.add(MemberInfoBonusData(2,"Đổi phần thưởng với số điểm ít hơn"))
        listData.add(MemberInfoBonusData(2,"Ưu đãi Klever Fruits hàng quý"))
        listData.add(MemberInfoBonusData(2,"Ưu đãi từ đối tác của Klever Fruits"))
        listData.add(MemberInfoBonusData(3,"Nhân đôi điểm thưởng"))
        listData.add(MemberInfoBonusData(3,"Đổi phần thưởng với số điểm ít hơn"))
        listData.add(MemberInfoBonusData(3,"Ưu đãi Klever Fruits hàng tháng"))
        listData.add(MemberInfoBonusData(3,"Ưu tiên chương trình freeship"))
        listData.add(MemberInfoBonusData(3,"Đường dây hỗ trợ khách hàng Bạch Kim riêng"))
        listData.add(MemberInfoBonusData(3,"Phiếu quà tặng độc quyền từ Klever Fruits"))

        var listDataReturn : ArrayList<MemberInfoBonusData> = ArrayList()

        for(item in listData){
            if(item.type == type){
                listDataReturn.add(item)
            }
        }

        return listDataReturn
    }
}