package com.example.kleverfruits.ui.dialog.login

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.ui.dialog.listStore.ListStoreDialogFragment
import com.example.kleverfruits.ui.dialog.listStore.ListStorePresenter
import com.example.kleverfruits.ui.dialog.listStore.ListStoreView
import com.example.kleverfruits.ui.dialog.signup.SignUpDialogFragment
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.LOGIN_STATUS
import com.example.kleverfruits.utils.`object`.LoginDialogs
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_main.*


class LoginDialogFragment : BaseDialogFragment<LoginPresenter>(), LoginView {
    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.activity_login, container, false)


        rootView.sign_up_now.setOnClickListener(View.OnClickListener {
            val newFragment = SignUpDialogFragment.newInstance(activity!!)
            newFragment.show(activity!!.supportFragmentManager,"")
//            drawer_layout.closeDrawer(GravityCompat.START)
        })

        rootView.revert_password.setOnClickListener(View.OnClickListener {
            LoginDialogs.showDialogRevertPassword(activity!!,{ dialogSmall: Dialog, email : String ->
                dialogSmall.dismiss()
            })
        })

        rootView.txt_login.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
            val editor = activity!!.prefs!!.edit()
            editor.putInt(LOGIN_STATUS, 1)
            editor.apply()
            activity!!.checkStatus()
        })

        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
                dialog.dismiss()
                activity!!.checkStatus()
            }
            false
        })
        return rootView
    }

    companion object {
        fun newInstance(activity: MainActivity): LoginDialogFragment {
            val f = LoginDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            f.arguments = args

            return f
        }
    }
}