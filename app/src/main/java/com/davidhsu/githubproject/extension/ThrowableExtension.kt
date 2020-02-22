package com.davidhsu.githubproject.extension

import timber.log.Timber

fun Throwable.report(tag: String = "githubProject"){
    Timber.tag(tag).e(this)
}