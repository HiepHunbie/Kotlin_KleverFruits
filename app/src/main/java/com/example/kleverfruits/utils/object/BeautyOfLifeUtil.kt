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
import com.example.kleverfruits.model.bonus.DataBonus
import kotlinx.android.synthetic.main.dialog_beauty_of_life_detail.view.*

object BeautyOfLifeUtil {

    fun showDialogBeautyOfLifeDetail(parentActivity: Activity, dataBeautyOfLifeDetail: DataBeautyOfLifeDetail, clickItemBaclListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_beauty_of_life_detail, null)
        val mDialog = Dialog(parentActivity, R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialogView.webview_detail_beauty_of_life.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        mDialogView.webview_detail_beauty_of_life.loadUrl(dataBeautyOfLifeDetail.url)
        mDialogView.webview_detail_beauty_of_life!!.settings.javaScriptEnabled = true

        if(dataBeautyOfLifeDetail.image.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(parentActivity,dataBeautyOfLifeDetail.image!!,mDialogView?.img_top!!)
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