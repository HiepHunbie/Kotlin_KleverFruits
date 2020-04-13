package com.example.kleverfruits.utils.`object`

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.kleverfruits.R
import com.example.kleverfruits.model.beautyOfLife.DataBeautyOfLifeDetail
import com.example.kleverfruits.model.sale.DataSale
import kotlinx.android.synthetic.main.dialog_sale_detail.view.*

object SaleUtil {

    fun showDialogBeautyOfLifeDetail(parentActivity: Activity, dataSale: DataSale, clickItemBaclListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_sale_detail, null)
        val mDialog = Dialog(parentActivity, R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialogView.webview_detail_sale.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        mDialogView.webview_detail_sale.loadUrl(dataSale.url)
        mDialogView.webview_detail_sale!!.settings.javaScriptEnabled = true

        if(dataSale.image.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(parentActivity,dataSale.image!!,mDialogView?.img_top!!)
        }
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