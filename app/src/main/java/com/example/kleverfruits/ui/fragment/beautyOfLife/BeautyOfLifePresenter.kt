package com.example.kleverfruits.ui.fragment.beautyOfLife

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.levelFruits.LevelFruitsView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class BeautyOfLifePresenter (mainView: BeautyOfLifeView) : BasePresenter<BeautyOfLifeView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}