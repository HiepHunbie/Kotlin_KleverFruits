package com.example.kleverfruits.ui.fragment.levelGifts

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class LevelGiftsPresenter (mainView: LevelGiftsView) : BasePresenter<LevelGiftsView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}