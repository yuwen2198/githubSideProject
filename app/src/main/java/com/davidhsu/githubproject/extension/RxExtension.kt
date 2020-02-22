package com.davidhsu.githubproject.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

fun Disposable.addTo(compositeDisposable: CompositeDisposable?): Disposable =
    apply {
        if (compositeDisposable?.isDisposed == true) {
            Timber.e("compositeDisposable disposed, ignore but a leak may happen!")
            RuntimeException("compositeDisposable disposed").report()
            return@apply
        }
        compositeDisposable?.add(this)
    }