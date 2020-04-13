package com.example.kleverfruits.ui.fragment.flashSale

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.search.SearchView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class FlashSalePresenter (mainView: FlashSaleView) : BasePresenter<FlashSaleView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}