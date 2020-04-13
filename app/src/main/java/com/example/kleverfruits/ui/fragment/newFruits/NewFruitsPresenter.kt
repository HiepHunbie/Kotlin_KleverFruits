package com.example.kleverfruits.ui.fragment.newFruits

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.flashSale.FlashSaleView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NewFruitsPresenter (mainView: NewFruitsView) : BasePresenter<NewFruitsView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}