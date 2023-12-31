package com.app.composemusicplayer.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.app.bottomtabscompose.ui.composables.BottomNavigationBar
import com.app.bottomtabscompose.ui.composables.NavHostContainer
import com.app.composemusicplayer.viewmodels.HomeViewModel
import com.dawinder.musicplayer_jetpackcompose.ui.theme.MusicPlayerJetpackComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerJetpackComposeTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = Color.Black // Change this to the desired color
                SideEffect {
                    systemUiController.setStatusBarColor(statusBarColor)
                }
                val navController = rememberNavController()
                Surface(color = Color.Black) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = { padding ->
                            NavHostContainer(navController = navController, padding = padding,viewModel)
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicPlayerJetpackComposeTheme {

    }
}