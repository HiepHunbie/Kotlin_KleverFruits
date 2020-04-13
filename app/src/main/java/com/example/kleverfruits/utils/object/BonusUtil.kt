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
import com.example.kleverfruits.R
import com.example.kleverfruits.model.bonus.DataBonus
import com.example.kleverfruits.model.notification.DataNotification
import kotlinx.android.synthetic.main.dialog_bonus_klever_detail.view.*
import kotlinx.android.synthetic.main.dialog_bottom_conver_point.view.*
import kotlinx.android.synthetic.main.dialog_revert_password.view.*

object BonusUtil {
    fun showDialogBonusDetail(parentActivity: Activity, dataBonus: DataBonus, clickItemConvertListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_bonus_klever_detail, null)
        val mDialog = Dialog(parentActivity, R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialogView.webview_detail_bonus_klever.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        mDialogView.webview_detail_bonus_klever.loadUrl(dataBonus.url_detail)
        mDialogView.webview_detail_bonus_klever!!.settings.javaScriptEnabled = true
        mDialogView.img_back.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
        mDialogView.txt_convert_point.setOnClickListener(View.OnClickListener {
            clickItemConvertListener(mDialog,"")
        })
        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
                mDialog.dismiss()
            }
            false
        })
    }

    fun showDialogConvertPoint(parentActivity: Activity, dataBonus: DataBonus, clickItemListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_bottom_conver_point, null)
        val mDialog = Dialog(parentActivity,R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        var text = String.format(parentActivity.getString(R.string.convert_this_bonus), dataBonus.count.toString())
        mDialogView.txt_convert_with.setText(text)
        mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.txt_confirm.setOnClickListener(View.OnClickListener {
            clickItemListener(mDialog,"")
        })
        mDialogView.txt_cancel.setOnClickListener(View.OnClickListener {
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