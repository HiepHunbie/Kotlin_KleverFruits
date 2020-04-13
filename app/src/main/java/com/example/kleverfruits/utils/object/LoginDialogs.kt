package com.example.kleverfruits.utils.`object`

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import android.view.View
import com.example.kleverfruits.R
import kotlinx.android.synthetic.main.dialog_change_password.view.*
import kotlinx.android.synthetic.main.dialog_revert_password.view.*
import kotlinx.android.synthetic.main.dialog_select_calendar.view.*

object LoginDialogs {

    fun showDialogRevertPassword(parentActivity: Activity, clickItemListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_revert_password, null)
        val mDialog = Dialog(parentActivity,R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.txt_revert_password.setOnClickListener(View.OnClickListener {
            clickItemListener(mDialog,"")
        })
        mDialogView.layout_close.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
//        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_BACK){
//                mDialog.dismiss()
//            }
//            false
//        })
    }

    fun showDialogChangePassword(parentActivity: Activity, clickItemListener: (Dialog, String) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_change_password, null)
        val mDialog = Dialog(parentActivity,R.style.DialogCustom)
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()

        mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.txt_done_change_password.setOnClickListener(View.OnClickListener {
            clickItemListener(mDialog,"")
        })
        mDialogView.layout_close_change_password.setOnClickListener(View.OnClickListener {
            mDialog.dismiss()
        })
//        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_BACK){
//                mDialog.dismiss()
//            }
//            false
//        })
    }

    fun showDialogSelectCalendar(parentActivity: Activity, clickDoneListener: (String,Dialog) -> Unit){
        val mDialogView = parentActivity.layoutInflater.inflate(R.layout.dialog_select_calendar, null)
        val mDialog = Dialog(parentActivity)
        mDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog.setContentView(mDialogView)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()
        var time = ""
        var hourSet = ""

        mDialogView.calendar_select?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            time = ""+ dayOfMonth + "-" + (month + 1) + "-" + year
        }
        mDialogView.txt_done_select_calendar.setOnClickListener {
            clickDoneListener(hourSet + " "+time,mDialog)
        }
        mDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
                mDialog.dismiss()
            }
            false
        })
    }
}