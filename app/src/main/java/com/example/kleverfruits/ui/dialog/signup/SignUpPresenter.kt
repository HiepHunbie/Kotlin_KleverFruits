package com.example.kleverfruits.ui.dialog.signup

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.login.LoginView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SignUpPresenter (mainView: SignUpView) : BasePresenter<SignUpView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}