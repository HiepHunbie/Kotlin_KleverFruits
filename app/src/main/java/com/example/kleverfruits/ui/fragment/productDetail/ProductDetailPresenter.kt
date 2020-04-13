package com.example.kleverfruits.ui.fragment.productDetail

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.search.SearchView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ProductDetailPresenter (mainView: ProductDetailView) : BasePresenter<ProductDetailView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}