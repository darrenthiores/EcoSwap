package com.darrenthiores.ecoswap.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.darrenthiores.ecoswap.android.domain.core.preferences.Preferences
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoSwapTheme {
                EcoSwap(
                    shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()
                )
            }
        }
    }
}
