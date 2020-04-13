package com.example.kleverfruits.ui.dialog.transitInfo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.model.order.DataOrder
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.dialog.order.OrderDetailAdapter
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailDialogFragment
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailPresenter
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailView
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.fragment_transit_info.view.*

class TransitInfoDialogFragment : BaseDialogFragment<TransitInfoPresenter>(), TransitInfoView {
    override fun instantiatePresenter(): TransitInfoPresenter {
        return TransitInfoPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null
    var dataOrder : DataOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        this.dataOrder = arguments!!.getParcelable<DataOrder>("dataOrder")
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_transit_info, container, false)


        rootView.img_back_transit_info.setOnClickListener(View.OnClickListener {
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
        fun newInstance(activity: MainActivity, dataOrder: DataOrder): TransitInfoDialogFragment {
            val f = TransitInfoDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            args.putParcelable("dataOrder", dataOrder)
            f.arguments = args

            return f
        }
    }
}