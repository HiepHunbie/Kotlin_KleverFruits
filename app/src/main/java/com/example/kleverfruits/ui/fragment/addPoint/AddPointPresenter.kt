package com.example.kleverfruits.ui.fragment.addPoint

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.levelGifts.LevelGiftsView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AddPointPresenter (mainView: AddPointView) : BasePresenter<AddPointView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}