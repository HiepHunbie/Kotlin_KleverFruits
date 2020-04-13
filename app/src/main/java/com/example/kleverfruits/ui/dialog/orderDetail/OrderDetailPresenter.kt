package com.example.kleverfruits.ui.dialog.orderDetail

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.order.OrderView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class OrderDetailPresenter (mainView: OrderDetailView) : BasePresenter<OrderDetailView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}