package com.example.kleverfruits.ui.splash

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SplashPresenter (mainView: SplashView) : BasePresenter<SplashView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}