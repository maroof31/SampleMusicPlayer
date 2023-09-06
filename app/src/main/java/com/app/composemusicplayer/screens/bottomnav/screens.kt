package com.app.bottomtabscompose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.composemusicplayer.R
import com.dawinder.musicplayer_jetpackcompose.ui.composable.HomeScreenParent
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: HomeViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues = padding),
            builder = {
                composable("home") {
                    HomeScreenParent(viewModel = viewModel)
                }
                composable("search") {
                    HomeScreenParent(viewModel = viewModel)
                }
            })
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        // set background color
        backgroundColor = Color(0xFF000000)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            val isSelected = currentRoute == navItem.route
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(navItem.route)
                },
                icon = {
                    LabelAboveIconBottomNavigationItem(
                        isSelected = isSelected,
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(navItem.route)
                        },
                        icon = navItem.icon,
                        label = navItem.label
                    )
                },
            )
        }
    }
}
@Composable
fun LabelAboveIconBottomNavigationItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: Int,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else Color.Gray, // Adjust text color as needed
        )
        Spacer(modifier = Modifier.height(4.dp))
        if(isSelected) {
            Image(
               painter=painterResource(icon),
                contentDescription = label,
                modifier = Modifier.size(8.dp)
            )
        }
    }
}

