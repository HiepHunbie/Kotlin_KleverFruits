package com.example.kleverfruits.utils.`object`

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleverfruits.R
import com.example.kleverfruits.model.levelFruits.DataFilterLevelFruits
import com.example.kleverfruits.model.notification.DataNotification
import com.example.kleverfruits.ui.fragment.levelFruits.FilterLevelFruitsAdapter
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.dialog_notification_detail.view.*
import kotlinx.android.synthetic.main.dialog_revert_password.view.*
import kotlinx.android.synthetic.main.dialog_sale_code.view.*
import kotlinx.android.synthetic.main.dialog_type_pay.view.*
import kotlinx.android.synthetic.main.layout_order_success.view.*

object CartUtil {
    fun showDialogTypePay(parentActivity: MainActivity, listDataFilterLevelFruits: ArrayList<DataFilterLevelFruits>, typeChecked : String, clickItemListener: (Dialog, Int, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_type_pay, null)
        val mDialog = Dialog(parentActivity, R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        var lilterLevelFruitsAdapter : FilterLevelFruitsAdapter? = null

        lilterLevelFruitsAdapter = FilterLevelFruitsAdapter(parentActivity!!, { itemDto: DataFilterLevelFruits, position: Int ->
            clickItemListener(mDialog,position,listDataFilterLevelFruits[position].id)
            mDialog.dismiss()
        })

        mDialogView.ryv_type_pay!!.adapter = lilterLevelFruitsAdapter
        mDialogView.ryv_type_pay!!.layoutManager = LinearLayoutManager(parentActivity, LinearLayoutManager.VERTICAL!!, false)
        lilterLevelFruitsAdapter!!.updatePosts(listDataFilterLevelFruits,typeChecked,false)

        mDialogView.img_back_type_pay.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
                mDialog.dismiss()
            }
            false
        })
    }

    fun showDialogSaleCode(parentActivity: Activity, clickItemListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_sale_code, null)
        val mDialog = Dialog(parentActivity,R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.txt_apply.setOnClickListener(View.OnClickListener {
            clickItemListener(mDialog,mDialogView.edt_sale_code.text.toString())
        })
        mDialogView.layout_close_sale_code.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
//        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_BACK){
//                mDialog.dismiss()
//            }
//            false
//        })
    }

    fun showDialogOrderSuccess(parentActivity: Activity, clickItemListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.layout_order_success, null)
        val mDialog = Dialog(parentActivity,R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.txt_come_back_home.setOnClickListener(View.OnClickListener {
            clickItemListener(mDialog,"")
        })
        mDialogView.layout_close_order_success.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
//        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_BACK){
//                mDialog.dismiss()
//            }
//            false
//        })
    }
}