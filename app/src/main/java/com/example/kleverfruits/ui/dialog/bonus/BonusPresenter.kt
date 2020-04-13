package com.example.kleverfruits.ui.dialog.bonus

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.cart.CartView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class BonusPresenter (mainView: BonusView) : BasePresenter<BonusView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}