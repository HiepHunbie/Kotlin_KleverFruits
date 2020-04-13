package com.example.kleverfruits.utils.`object`

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.kleverfruits.R
import kotlinx.android.synthetic.main.bottom_select_picture_dialog.view.*

object ImageUtil {
    fun showDialogBottomSelectPicture(parentActivity: Activity, clickItemListener: (Dialog, Int) -> Unit){
        val view = parentActivity.layoutInflater.inflate(R.layout.bottom_select_picture_dialog, null)
        val mBuilder = AlertDialog.Builder(parentActivity,R.style.DialogCustom).setView(view)
        //show dialog
        val  mAlertDialog = mBuilder.show()
        var sort : Int = 0
//        mAlertDialog.setCancelable(false)
//        mAlertDialog.setCanceledOnTouchOutside(false)
        mAlertDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val dialog = BottomSheetDialog(parentActivity)
//        val dialog = Dialog(parentActivity,R.style.DialogCustomNotFull)
//        dialog.setContentView(view)
        mAlertDialog.show()
        view.img_close_popup_picture.setOnClickListener(View.OnClickListener {
            mAlertDialog.dismiss()
        })

        view.layout_capture_image.setOnClickListener(View.OnClickListener {
            clickItemListener(mAlertDialog,0)
            mAlertDialog.dismiss()
        })
        view.layout_get_picture_from_gallery.setOnClickListener(View.OnClickListener {
            clickItemListener(mAlertDialog,1)
            mAlertDialog.dismiss()
        })
        mAlertDialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
                mAlertDialog.dismiss()
            }
            false
        })

    }


    fun loadImageGlide(context: Context, path : String, imageView : ImageView, progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        Glide.with(context).load(path).apply( RequestOptions().placeholder(R.drawable.ic_error_image_loaded).error(R.drawable.ic_error_image_loaded)).listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar!!.visibility = View.GONE
//                imageView.visibility = View.VISIBLE
//                imageView.setImageResource(R.drawable.ic_back_1_sale_detail)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar!!.visibility = View.GONE
                return false
            }

        }).into(imageView!!)
    }
    fun loadImageGlideNotProgressBar(context: Context, path : String, imageView : ImageView){
        Glide.with(context).load(path).apply( RequestOptions().placeholder(R.drawable.ic_error_image_loaded).error(R.drawable.ic_error_image_loaded)).listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
//                imageView.visibility = View.VISIBLE
//                imageView.setImageResource(R.drawable.ic_back_1_sale_detail)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        }).into(imageView!!)
    }
}