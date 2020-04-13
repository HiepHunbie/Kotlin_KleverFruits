package com.example.kleverfruits.ui.dialog.cart

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.order.OrderView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CartPresenter (mainView: CartView) : BasePresenter<CartView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}