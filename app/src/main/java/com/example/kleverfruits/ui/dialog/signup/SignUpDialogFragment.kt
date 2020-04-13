package com.example.kleverfruits.ui.dialog.signup

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.ui.dialog.login.LoginDialogFragment
import com.example.kleverfruits.ui.dialog.login.LoginPresenter
import com.example.kleverfruits.ui.dialog.login.LoginView
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_signup.view.*

class SignUpDialogFragment : BaseDialogFragment<SignUpPresenter>(), SignUpView {
    override fun instantiatePresenter(): SignUpPresenter {
        return SignUpPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.activity_signup, container, false)

        rootView.txt_login_now.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })

//        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                dialog.dismiss()
//            }
//            false
//        })
        return rootView
    }

    companion object {
        fun newInstance(activity: MainActivity): SignUpDialogFragment {
            val f = SignUpDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            f.arguments = args

            return f
        }
    }
}