package com.example.kleverfruits.ui.fragment.search

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.home.HomeView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchPresenter (mainView: SearchView) : BasePresenter<SearchView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}