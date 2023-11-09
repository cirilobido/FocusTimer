package com.cirilobido.focustimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cirilobido.focustimer.presentation.home.HomeScreen
import com.cirilobido.focustimer.presentation.home.HomeScreenViewModel
import com.cirilobido.focustimer.presentation.theme.FocusTimerTheme

internal val viewModel: HomeScreenViewModel = HomeScreenViewModel()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FocusTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    HomeScreen(viewModel)
                }
            }
        }
    }
}