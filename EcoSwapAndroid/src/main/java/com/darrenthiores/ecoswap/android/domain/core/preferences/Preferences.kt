package com.darrenthiores.ecoswap.android.domain.core.preferences

import com.darrenthiores.ecoswap.android.BuildConfig

interface Preferences {
    fun saveShouldShowOnBoarding(shouldShow: Boolean)
    fun loadShouldShowOnBoarding(): Boolean
    fun saveShouldShowAuth(shouldShow: Boolean)
    fun loadShouldShowAuth(): Boolean

    companion object {
        const val SHOULD_SHOW_ON_BOARDING = BuildConfig.SHOW_ON_BOARDING_KEY
        const val SHOULD_SHOW_AUTH = BuildConfig.SHOW_AUTH_KEY
    }
}