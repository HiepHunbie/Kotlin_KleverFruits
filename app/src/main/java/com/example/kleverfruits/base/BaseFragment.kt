package com.example.kleverfruits.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kleverfruits.ui.main.MainActivity


abstract class BaseFragment<P : com.example.kleverfruits.base.BasePresenter<com.example.kleverfruits.base.BaseView>>  : Fragment(),
    com.example.kleverfruits.base.MVPView {

    var parentActivity: MainActivity? = null
    private var progressDialog: ProgressDialog? = null
    open var presenter: P? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            val activity = context as MainActivity?
            this.parentActivity = activity
//            activity?.onFragmentAttached()
        }
        this.retainInstance = retainInstance()
        presenter = instantiatePresenter()
    }

    private fun retainInstance(): Boolean {
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showProgress() {
        hideProgress()
//        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    fun getMainActivity() = parentActivity

//    override fun onBackPressed() {}
//    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)
protected abstract fun instantiatePresenter(): P

}