package com.example.kleverfruits.ui.fragment.levelFruits

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class LevelFruitsPresenter (mainView: LevelFruitsView) : BasePresenter<LevelFruitsView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}