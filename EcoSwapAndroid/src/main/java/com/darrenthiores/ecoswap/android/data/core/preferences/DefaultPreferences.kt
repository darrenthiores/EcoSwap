package com.darrenthiores.ecoswap.android.data.core.preferences

import android.content.SharedPreferences
import com.darrenthiores.ecoswap.android.domain.core.preferences.Preferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DefaultPreferences @Inject constructor(
    @Named("sharedPreferences") private val sharedPref: SharedPreferences
): Preferences {
    override fun saveShouldShowOnBoarding(shouldShow: Boolean) {
        sharedPref
            .edit()
            .putBoolean(Preferences.SHOULD_SHOW_ON_BOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnBoarding(): Boolean =
        sharedPref.getBoolean(Preferences.SHOULD_SHOW_ON_BOARDING, true)

    override fun saveShouldShowAuth(shouldShow: Boolean) {
        sharedPref
            .edit()
            .putBoolean(Preferences.SHOULD_SHOW_AUTH, shouldShow)
            .apply()
    }

    override fun loadShouldShowAuth(): Boolean =
        sharedPref.getBoolean(Preferences.SHOULD_SHOW_AUTH, true)
}