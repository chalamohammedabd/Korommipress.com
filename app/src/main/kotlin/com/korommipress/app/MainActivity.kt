package com.korommipress.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.korommipress.app.ui.KorommiPressApp
import com.korommipress.app.ui.theme.KorommiPressTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        // Optional: Ensure Google Play Services are installed
        ModuleInstall.getClient(this).deferredInstall(emptyList())

        setContent {
            val isDarkTheme = isSystemInDarkTheme()
            
            KorommiPressTheme(darkTheme = isDarkTheme) {
                SideEffect {
                    // Set system bar colors
                    window.statusBarColor = Color.Transparent
                    window.navigationBarColor = Color.Transparent
                }
                
                KorommiPressApp()
            }
        }
    }
}
