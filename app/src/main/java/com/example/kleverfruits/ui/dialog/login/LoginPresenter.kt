package com.example.kleverfruits.ui.dialog.login

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.listStore.ListStoreView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class LoginPresenter (mainView: LoginView) : BasePresenter<LoginView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}