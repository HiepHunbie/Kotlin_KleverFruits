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
import com.example.kleverfruits.model.notification.DataNotification
import kotlinx.android.synthetic.main.dialog_notification_detail.view.*

object NotificationUtil {

    fun showDialogNotificationDetail(parentActivity: Activity, dataNotification: DataNotification){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_notification_detail, null)
        val mDialog = Dialog(parentActivity, R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialogView.webview_detail_notification.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        mDialogView.webview_detail_notification.loadUrl(dataNotification.url)
        mDialogView.webview_detail_notification!!.settings.javaScriptEnabled = true
        mDialogView.img_back.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
                mDialog.dismiss()
            }
            false
        })
    }
}