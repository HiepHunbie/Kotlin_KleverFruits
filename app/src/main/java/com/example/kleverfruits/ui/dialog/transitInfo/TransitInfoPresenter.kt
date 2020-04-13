package com.example.kleverfruits.ui.dialog.transitInfo

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class TransitInfoPresenter (mainView: TransitInfoView) : BasePresenter<TransitInfoView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}