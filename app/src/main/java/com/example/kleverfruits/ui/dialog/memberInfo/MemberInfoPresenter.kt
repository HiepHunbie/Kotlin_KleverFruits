package com.example.kleverfruits.ui.dialog.memberInfo

import com.example.kleverfruits.base.BasePresenter
import com.example.kleverfruits.network.PostApi
import com.example.kleverfruits.ui.dialog.bonus.BonusView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MemberInfoPresenter (mainView: MemberInfoView) : BasePresenter<MemberInfoView>(mainView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}