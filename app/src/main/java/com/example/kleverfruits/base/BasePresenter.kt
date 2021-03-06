package com.example.kleverfruits.base

import com.example.shop49k.injection.component.DaggerPresenterInjector
import com.example.shop49k.injection.component.PresenterInjector
import com.example.shop49k.injection.module.ContextModule
import com.example.shop49k.injection.module.NetworkModule


/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<out V : com.example.kleverfruits.base.BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
        }
    }
}