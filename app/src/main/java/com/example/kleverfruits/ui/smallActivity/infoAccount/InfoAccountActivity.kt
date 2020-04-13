package com.example.kleverfruits.ui.smallActivity.infoAccount

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.bumptech.glide.Glide
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseActivity
import com.example.kleverfruits.utils.LOGIN_STATUS
import com.example.kleverfruits.utils.ManagePermissions
import com.example.kleverfruits.utils.`object`.GetImageUtil
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.LoginDialogs
import kotlinx.android.synthetic.main.layout_info_account.*

class InfoAccountActivity : BaseActivity<InfoAccountPresenter>(), InfoAccountView {

    override fun instantiatePresenter(): InfoAccountPresenter {
        return InfoAccountPresenter(this)
    }

    private val PermissionsRequestCode = 123
    lateinit var managePermissions: ManagePermissions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_info_account)

        val list = listOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.READ_PHONE_STATE
        )

        managePermissions = ManagePermissions(this!!,list,PermissionsRequestCode)

        managePermissions.checkPermissions()

        img_birthday.setOnClickListener(View.OnClickListener {
            LoginDialogs.showDialogSelectCalendar(this,{ time:String, dialogNew: Dialog ->
                edt_birthday.setText(time)
                dialogNew.dismiss()
            })
        })

        txt_logout.setOnClickListener(View.OnClickListener {
            val editor = prefs!!.edit()
            editor.putInt(LOGIN_STATUS, 0)
            editor.apply()
            finish()
        })

        edt_change_password.setOnClickListener(View.OnClickListener {
            LoginDialogs.showDialogChangePassword(this!!,{ dialogSmall: Dialog, email : String ->
                dialogSmall.dismiss()
            })
        })

        img_change_avatar.setOnClickListener(View.OnClickListener {
            ImageUtil.showDialogBottomSelectPicture(this!!,{ dialogNew: Dialog, pos : Int ->
                if(pos == 0){
                    GetImageUtil.captureImage("avatar",this)
                }else if(pos == 1){
                    GetImageUtil.openAlbum(this)
                }
                dialogNew.dismiss()
            })
        })

        edt_account_mobile_phone.setOnValidityChange { view, isValid ->
            if(isValid){

            }else{
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PermissionsRequestCode ->{
                val isPermissionsGranted =managePermissions!!
                    .processPermissionsResult(requestCode,permissions,grantResults)

                if(isPermissionsGranted){
//                    addFragment(2)
//                    Dialogs.showToastErrorNewNotListenerClick(this,"Permissions ok.")
                }else{
//                    Dialogs.showToastErrorNewNotListenerClick(this,"Permissions denied.")
                }

                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            GetImageUtil.FINAL_TAKE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    val dir = Environment.getExternalStorageDirectory().toString()
                    val bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(GetImageUtil.imageUri!!))
                        img_avatar.setImageBitmap(bitmap)
                }
            GetImageUtil.FINAL_CHOOSE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
//                        4.4以上
                            Glide.with(this).load(GetImageUtil.handleImageOnKitkat(data,this)!!).into(img_avatar)
                    }
                    else{
//                        4.4以下
                        GetImageUtil.handleImageBeforeKitkat(data)
                    }
                }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}