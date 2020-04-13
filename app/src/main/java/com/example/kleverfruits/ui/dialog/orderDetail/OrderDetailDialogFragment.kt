package com.example.kleverfruits.ui.dialog.orderDetail

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.model.order.DataOrder
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.dialog.login.LoginDialogFragment
import com.example.kleverfruits.ui.dialog.login.LoginPresenter
import com.example.kleverfruits.ui.dialog.order.OrderDetailAdapter
import com.example.kleverfruits.ui.dialog.signup.SignUpDialogFragment
import com.example.kleverfruits.ui.dialog.transitInfo.TransitInfoDialogFragment
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.LOGIN_STATUS
import com.example.kleverfruits.utils.`object`.LoginDialogs
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.fragment_order_detail.view.*

class OrderDetailDialogFragment : BaseDialogFragment<OrderDetailPresenter>(), OrderDetailView {
    override fun instantiatePresenter(): OrderDetailPresenter {
        return OrderDetailPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null
    var dataOrder : DataOrder? = null
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        this.dataOrder = arguments!!.getParcelable<DataOrder>("dataOrder")
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    var txt_address : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_order_detail, container, false)
        myClipboard = activity!!.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?;
        txt_address = rootView.findViewById(R.id.txt_address_detail)
        rootView.txt_order_code.setText(dataOrder!!.order_code.toString() ?: "")
        rootView.txt_date.setText(dataOrder!!.date?: "")
        if(dataOrder!!.status == 0){
            rootView.txt_status.setText(context.getString(R.string.not_pay))
        }else if(dataOrder!!.status == 1){
            rootView.txt_status.setText(context.getString(R.string.payed))
        }
        rootView.txt_transit_date.setText(dataOrder!!.date?: "")

        rootView.txt_copy.setOnClickListener(View.OnClickListener {
            var text = txt_address!!.text.toString()
            myClip = ClipData.newPlainText("text", text);
            myClipboard?.setPrimaryClip(myClip);
        })
        var orderDetailAdapter : OrderDetailAdapter? = null
        orderDetailAdapter = OrderDetailAdapter(activity!!, { itemDto: DataOrderDetail, position: Int ->

        })

        rootView?.ryv_order_detail!!.adapter = orderDetailAdapter
        rootView?.ryv_order_detail!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
        orderDetailAdapter!!.updatePosts(dataOrder!!.dataOrderDetail,false)

        var totalCost = 0
        for(itemSmall in dataOrder!!.dataOrderDetail){
            totalCost = totalCost + (itemSmall.cost.toInt() * itemSmall.number)
        }
        rootView.txt_total_product_cost.setText(NumberUtil.convertNumberToPrice(totalCost.toDouble(), context))
        rootView.txt_total_cost.setText(NumberUtil.convertNumberToPrice(totalCost.toDouble(), context))

        rootView.txt_view.setOnClickListener(View.OnClickListener {
            val newFragment = TransitInfoDialogFragment.newInstance(activity!!, dataOrder!!)
            newFragment.show(activity!!.supportFragmentManager,"")
        })
        rootView.img_back_order_detail.setOnClickListener(View.OnClickListener {
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
        fun newInstance(activity: MainActivity, dataOrder: DataOrder): OrderDetailDialogFragment {
            val f = OrderDetailDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            args.putParcelable("dataOrder", dataOrder)
            f.arguments = args

            return f
        }
    }
}