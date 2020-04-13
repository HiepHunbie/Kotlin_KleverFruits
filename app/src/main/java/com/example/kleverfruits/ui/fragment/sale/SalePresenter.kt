package com.example.kleverfruits.ui.fragment.sale

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.productDetail.ProductDetailView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SalePresenter (mainView: SaleView) : BasePresenter<SaleView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}