package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fon_classroommanagment_frontend.presentation.main_screen.components.Main_Screen
import com.example.fon_classroommanagment_frontend.Login_Screen
import com.example.fon_classroommanagment_frontend.MyClassroomRequests_Screen
import com.example.fon_classroommanagment_frontend.SignUp_Screen
import com.example.fon_classroommanagment_frontend.Splash_Screen
import com.example.fon_classroommanagment_frontend.common.RequestReservastion
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.presentation.my_classroom_request_screen.RequestViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.Aditional_Info_Screen
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.TypeEMP_EducationEMP_Screen
import com.example.fon_classroommanagment_frontend.screens.Appointment_Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController=rememberNavController()
    //Aditional_Info_Screen()
    //TypeEMP_EducationEMP_Screen()
    val    requestViewMode: RequestViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){

            Splash_Screen(navController)
        }
        composable(route = Screen.LoginScreen.route){
            BackHandler(true) {

            }
            Login_Screen(navController)
        }
        composable(route = Screen.RegisterScreen.route){
            SignUp_Screen(navController)
        }
        composable(route = Screen.AditionalInfoScreen.route)
        {

            val registerObject= navController.previousBackStackEntry?.arguments?.getParcelable<UserRegistrationDTO>("registerObject")
            if (registerObject != null) {
                Aditional_Info_Screen(navController,registerObject)
            }
        }
        composable(route = Screen.TypeEMPEducationEMPScreen.route){
            val registerObject= navController.previousBackStackEntry?.arguments?.getParcelable<UserRegistrationDTO>("registerObject")

            if (registerObject != null) {
                TypeEMP_EducationEMP_Screen(registerObject, navHostController = navController)
            }
        }

//        composable(route= Screen.DetailsClassroomScreen.route){
//            DetailsClassroom_Screen(navController)
//        }
        composable(route= Screen.AppointmentScreen.route+"?classroomId={classroomId}",
            arguments = listOf(navArgument("classroomId"){
            type= NavType.LongType
            defaultValue=-1L

        })){
            BackHandler(true) {
                navController.navigate(Screen.MyClassroomRequests_Screen.route)
            }

            val classroomId=it.arguments?.getLong("classroomId")
            val reserveDTO=it.arguments?.getParcelable<ReserveDTO>("reserveDTO")

            if (classroomId != null) {

                    Appointment_Screen(classroomId,reserveDTO,navController)

            }
        }
        composable(route= Screen.DetailsClassroomScreen.route){

            val reserveDTO=it.arguments?.getParcelable<ReserveDTO>("reserveDTO")
            Log.i("cao",it.destination.arguments.toString())
            DetailsClassroom_Screen(navController)
        }


        composable(route= Screen.MyClassroomRequests_Screen.route

            ){
            BackHandler(true) {

            }
            val registerObject= navController.previousBackStackEntry?.arguments?.getParcelable<RequestReservastion>("RequestReservastion")
            val saved=navController.previousBackStackEntry?.arguments?.getBoolean("saved")
            Log.i("cao",saved.toString())

            MyClassroomRequests_Screen(navController,registerObject,requestViewMode,saved)
        }




        navigation(route = Screen.MainScreen.route, startDestination = Screen.BottomBarScreens.AllClassroomsScreen.route){

            composable(route = Screen.BottomBarScreens.AllClassroomsScreen.route){
                BackHandler(true) {

                }
               Main_Screen(navHostController = navController, Screen.BottomBarScreens.AllClassroomsScreen.title)

            }
            composable(route = Screen.BottomBarScreens.ReservationScreen.route){
                BackHandler(true) {

                }
                Main_Screen(navHostController = navController, Screen.BottomBarScreens.ReservationScreen.title)

            }
            composable(route = Screen.BottomBarScreens.UserProfileScreen.route){
                BackHandler(true) {

                }
                Main_Screen(navHostController = navController, Screen.BottomBarScreens.UserProfileScreen.title)
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.BottomBarScreens.AllClassroomsScreen.route) {


    }

}