package com.example.fon_classroommanagment_frontend.Components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.fon_classroommanagment_frontend.Components.input.Main_Screen
import com.example.fon_classroommanagment_frontend.Login_Screen
import com.example.fon_classroommanagment_frontend.Profile_Screen
import com.example.fon_classroommanagment_frontend.SignUp_Screen
import com.example.fon_classroommanagment_frontend.screens.AllReservations_Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(){
    val navController=rememberNavController()

    NavHost(navController = navController, startDestination =Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
            Login_Screen( { navController.navigate(route = Screen.RegisterScreen.route) }) {
                navController.navigate(
                    route = Screen.MainScreen.route
                )
            }
        }
        composable(route = Screen.RegisterScreen.route){
            SignUp_Screen { navController.navigate(route = Screen.LoginScreen.route) }
        }

        navigation(route = Screen.MainScreen.route, startDestination = Screen.BottomBarScreens.AllClassroomsScreen.route){
            composable(route = Screen.BottomBarScreens.AllClassroomsScreen.route){
               Main_Screen(navHostController = navController,Screen.BottomBarScreens.AllClassroomsScreen.title)
            }
            composable(route = Screen.BottomBarScreens.ReservationScreen.route){
                Main_Screen(navHostController = navController,Screen.BottomBarScreens.ReservationScreen.title)

            }
            composable(route = Screen.BottomBarScreens.UserProfileScreen.route){
                Main_Screen(navHostController = navController,Screen.BottomBarScreens.UserProfileScreen.title)
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination =Screen.BottomBarScreens.AllClassroomsScreen.route) {


    }

}