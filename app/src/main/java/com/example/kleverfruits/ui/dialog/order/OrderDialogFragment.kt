package com.example.kleverfruits.ui.dialog.order

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.model.order.DataOrder
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.dialog.login.LoginDialogFragment
import com.example.kleverfruits.ui.dialog.login.LoginPresenter
import com.example.kleverfruits.ui.dialog.login.LoginView
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailDialogFragment
import com.example.kleverfruits.ui.dialog.signup.SignUpDialogFragment
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.LOGIN_STATUS
import com.example.kleverfruits.utils.`object`.LoginDialogs
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_order.view.*

class OrderDialogFragment : BaseDialogFragment<OrderPresenter>(), OrderView {
    override fun instantiatePresenter(): OrderPresenter {
        return OrderPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null
    var orderAdapter : OrderAdapter? = null
    var listDataOrder : ArrayList<DataOrder> = ArrayList()
    var listDataOrderSelect : ArrayList<DataOrder> = ArrayList()
    var txt_back_order : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    fun getLisOrder(status: Int): ArrayList<DataOrder> {
        var list: ArrayList<DataOrder> = ArrayList()
        if(status == 999){
            list.addAll(listDataOrder)
        }else{
            for (item in listDataOrder) {
                if(item.status_ship == status){
                    list.add(item)
                }
            }
        }
        return list
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_order, container, false)

        rootView.layout_all.setOnClickListener(View.OnClickListener {
            rootView.txt_all.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_waiting_confirm.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipping.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipped.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_canceled.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.layout_all.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_waiting_confirm.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipping.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipped.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_canceled.setBackgroundResource(R.drawable.custom_border_bottom_white)
            orderAdapter!!.updatePosts(getLisOrder(999),false)
        })
        rootView.layout_waiting_confirm.setOnClickListener(View.OnClickListener {
            rootView.txt_all.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_waiting_confirm.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_shipping.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipped.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_canceled.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.layout_all.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_waiting_confirm.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_shipping.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipped.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_canceled.setBackgroundResource(R.drawable.custom_border_bottom_white)
            orderAdapter!!.updatePosts(getLisOrder(0),false)
        })
        rootView.layout_shipping.setOnClickListener(View.OnClickListener {
            rootView.txt_all.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_waiting_confirm.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipping.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_shipped.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_canceled.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.layout_all.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_waiting_confirm.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipping.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_shipped.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_canceled.setBackgroundResource(R.drawable.custom_border_bottom_white)
            orderAdapter!!.updatePosts(getLisOrder(1),false)
        })
        rootView.layout_shipped.setOnClickListener(View.OnClickListener {
            rootView.txt_all.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_waiting_confirm.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipping.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipped.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_canceled.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.layout_all.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_waiting_confirm.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipping.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipped.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_canceled.setBackgroundResource(R.drawable.custom_border_bottom_white)
            orderAdapter!!.updatePosts(getLisOrder(2),false)
        })
        rootView.layout_canceled.setOnClickListener(View.OnClickListener {
            rootView.txt_all.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_waiting_confirm.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipping.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_shipped.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_canceled.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.layout_all.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_waiting_confirm.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipping.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_shipped.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_canceled.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            orderAdapter!!.updatePosts(getLisOrder(3),false)
        })
        listDataOrder.add(DataOrder("303003","20/09/2019", "1",0,0,1, listOf(DataOrderDetail("","Việt Quất Mỹ","999000",2,1),
            DataOrderDetail("","Việt Quất Mỹ","999000",2,1),DataOrderDetail("","Việt Quất Mỹ","999000",2,1))))
        listDataOrder.add(DataOrder("303003","20/09/2019", "1",0,1,2, listOf(DataOrderDetail("","Việt Quất Mỹ","999000",2,2),
            DataOrderDetail("","Việt Quất Mỹ","999000",2,2))))
        listDataOrder.add(DataOrder("303003","20/09/2019", "1",0,0,3, listOf(DataOrderDetail("","Việt Quất Mỹ","999000",2,3),
            DataOrderDetail("","Việt Quất Mỹ","999000",2,3))))
        orderAdapter = OrderAdapter(activity!!, { itemDto: DataOrder, position: Int ->
            val newFragment = OrderDetailDialogFragment.newInstance(activity!!, itemDto)
            newFragment.show(activity!!.supportFragmentManager,"")
        })

        rootView?.ryv_order!!.adapter = orderAdapter
        rootView?.ryv_order!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
//        orderAdapter!!.updatePosts(listDataOrder,false)

        rootView.layout_all.callOnClick()

        rootView.img_back_my_order.setOnClickListener(View.OnClickListener {
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
        fun newInstance(activity: MainActivity): OrderDialogFragment {
            val f = OrderDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            f.arguments = args

            return f
        }
    }
}