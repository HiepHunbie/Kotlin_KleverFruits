package com.example.kleverfruits.ui.smallActivity.infoAccount

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.signup.SignUpView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class InfoAccountPresenter (mainView: InfoAccountView) : BasePresenter<InfoAccountView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}