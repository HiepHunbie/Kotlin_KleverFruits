package com.example.kleverfruits.ui.dialog.cart

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.model.cart.DataCart
import com.example.kleverfruits.model.cart.cartDetail.DataCartDetail
import com.example.kleverfruits.model.levelFruits.DataFilterLevelFruits
import com.example.kleverfruits.model.order.DataOrder
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.dialog.order.OrderAdapter
import com.example.kleverfruits.ui.dialog.order.OrderDialogFragment
import com.example.kleverfruits.ui.dialog.order.OrderPresenter
import com.example.kleverfruits.ui.dialog.order.OrderView
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailDialogFragment
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.CartUtil
import com.example.kleverfruits.utils.`object`.GetImageUtil
import com.example.kleverfruits.utils.`object`.LoginDialogs
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.fragment_cart.view.*

class CartDialogFragment : BaseDialogFragment<CartPresenter>(), CartView {
    override fun instantiatePresenter(): CartPresenter {
        return CartPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null
    var cartAdapter : CartAdapter? = null
    var listDataCartDetail : ArrayList<DataCartDetail> = ArrayList()
    var dataCart : DataCart? = null
    var listDataFilterLevelFruits : ArrayList<DataFilterLevelFruits> = ArrayList()
    var typeCheck = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_cart, container, false)

        listDataFilterLevelFruits.add(DataFilterLevelFruits(parentActivity!!.getString(R.string.pay_at_home), "0"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits(parentActivity!!.getString(R.string.pay_by_bank), "1"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits(parentActivity!!.getString(R.string.pay_by_atm), "2"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits(parentActivity!!.getString(R.string.pay_by_momo), "3"))
        listDataFilterLevelFruits.add(DataFilterLevelFruits(parentActivity!!.getString(R.string.pay_by_moca), "4"))

        dataCart = DataCart(0,"1990000","0","0","999000",
            listOf(DataCartDetail("Viet quat my", 1, "1200000","999000",""),
                DataCartDetail("Viet quat my", 2, "1200000","999000",""),
                DataCartDetail("Viet quat my", 1, "1200000","999000",""),
                DataCartDetail("Viet quat my", 3, "1200000","999000","")))
        listDataCartDetail.addAll(dataCart!!.listCartDetail)

        cartAdapter = CartAdapter(activity!!, { itemDto: DataCartDetail, position: Int ->

        },{ itemDto: DataCartDetail, position: Int ->
            listDataCartDetail.removeAt(position)
            cartAdapter!!.updatePosts(listDataCartDetail,false)
        })

        rootView?.ryv_cart!!.adapter = cartAdapter
        rootView?.ryv_cart!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
        cartAdapter!!.updatePosts(listDataCartDetail,false)

        rootView?.txt_type_pay.setOnClickListener(View.OnClickListener {
            CartUtil.showDialogTypePay(parentActivity!!,listDataFilterLevelFruits,typeCheck,{ dialogNew: Dialog, pos : Int, type : String ->
                typeCheck = type
                rootView?.txt_type_pay.setText(listDataFilterLevelFruits[pos].name.toString())
                dialogNew.dismiss()
            })
        })

        rootView?.txt_sale_code.setOnClickListener(View.OnClickListener {
            CartUtil.showDialogSaleCode(activity!!,{ dialogSmall: Dialog, email : String ->
                dialogSmall.dismiss()
            })
        })

        rootView?.txt_buy_now_cart.setOnClickListener(View.OnClickListener {
            CartUtil.showDialogOrderSuccess(activity!!,{ dialogSmall: Dialog, email : String ->
                parentActivity!!.addFragment(0)
                dialog.dismiss()
                dialogSmall.dismiss()
            })
        })

        rootView?.txt_total_cost.setText(NumberUtil.convertNumberToPrice(dataCart!!.total_cost.toDouble(), context))
        rootView?.txt_total_product_cost.setText(NumberUtil.convertNumberToPrice(dataCart!!.total_product_cost.toDouble(), context))
        rootView?.txt_ship_cost.setText(NumberUtil.convertNumberToPrice(dataCart!!.shipping_cost.toDouble(), context))

        rootView.img_back_cart.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
        rootView.img_back_cart_bottom.setOnClickListener(View.OnClickListener {
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
        fun newInstance(activity: MainActivity): CartDialogFragment {
            val f = CartDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            f.arguments = args

            return f
        }
    }
}