package com.example.fon_classroommanagment_frontend.Components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fon_classroommanagment_frontend.Components.Screen
import com.example.fon_classroommanagment_frontend.R

@Composable
fun BottonBar(navHostController: NavHostController) {

    val stackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination=stackEntry?.destination
BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
    if (currentDestination != null) {
        BottomNavigationItem(Screen.BottomBarScreens.AllClassroomsScreen,currentDestination,navHostController)
        BottomNavigationItem(Screen.BottomBarScreens.ReservationScreen,currentDestination,navHostController)
        BottomNavigationItem(Screen.BottomBarScreens.UserProfileScreen,currentDestination,navHostController)

    }
}

}

@Composable
fun RowScope.BottomNavigationItem(
screen: Screen.BottomBarScreens,
currentDestination:NavDestination,
navController: NavHostController
){
     val selected=currentDestination.hierarchy.any{
        it.route==screen.route
    }
    BottomNavigationItem(
        label = {Text(screen.title, style = MaterialTheme.typography.bodySmall, color = if(selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface)},
        icon = { Icon(painter = painterResource(id = if(selected)screen.iconFilled else  screen.icon), contentDescription ="Icon", modifier = Modifier.size(24.dp), tint = if(selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface)},
        selected = selected,
        onClick = { navController.navigate(screen.route)})


}