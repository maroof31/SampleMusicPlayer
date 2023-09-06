package com.app.bottomtabscompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dawinder.musicplayer_jetpackcompose.ui.composable.HomeScreenParent
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                HomeScreenParent(viewModel = viewModel)
            }

            // route : search
            composable("search") {
               HomeScreenParent(viewModel = viewModel)
            }
        })

}


//@Composable
//fun BottomNavigationBar(navController: NavHostController) {
//
//    BottomNavigation(
//        // set background color
//        backgroundColor = Color(0xFF000000)
//    ) {
//
//        // observe the backstack
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//
//        // observe current route to change the icon
//        // color,label color when navigated
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        // Bottom nav items we declared
//        Constants.BottomNavItems.forEach { navItem ->
//
//            BottomNavigationItem(
//                selected = currentRoute == navItem.route,
//
//                onClick = {
//                    navController.navigate(navItem.route)
//                },
//
//                icon = {
//                    Icon(imageVector = navItem.icon, contentDescription = navItem.label,
//                        tint = Color.White)
//                },
//
//                // label
//                label = {
//                    Text(text = navItem.label, color = Color.White)
//                },
//                alwaysShowLabel = true,
//            )
//        }
//    }
//}
//


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        // set background color
        backgroundColor = Color(0xFF000000)
    ) {
        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()


        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->
            // Check if the current route matches the navigation item's route
            val isSelected = currentRoute == navItem.route

            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(navItem.route)
                },
                icon = {
                    if (isSelected) {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.label,
                            tint = Color.White
                        )
                    }
                },
                // label
                label = {
                    Text(text = navItem.label, color = Color.White)
                },
                alwaysShowLabel = true,
            )
        }
    }
}

@Composable
fun BottomNavigationBar2(navController: NavHostController) {
    BottomNavigation(
        // set background color
        backgroundColor = Color(0xFF918F8F)
    ) {
        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            val isSelected = currentRoute == navItem.route
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.label,
                            tint = if (isSelected) Color.White else Color.Gray,
                            modifier = Modifier.size(5.dp)
                        )
                        Text(
                            text = navItem.label,
                            color = if (isSelected) Color.White else Color.Gray
                        )
                    }
                },

                label = {
                    Spacer(modifier = Modifier.height(2.dp))
                },
                alwaysShowLabel = true,
            )
        }
    }
}

