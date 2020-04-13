package com.example.kleverfruits.ui.fragment.home

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.main.MainView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomePresenter (mainView: HomeView) : BasePresenter<HomeView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}