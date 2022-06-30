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
import com.example.fon_classroommanagment_frontend.*
import com.example.fon_classroommanagment_frontend.presentation.main_screen.components.Main_Screen
import com.example.fon_classroommanagment_frontend.common.RequestReservastion
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.navigation.ReservationDTONav
import com.example.fon_classroommanagment_frontend.presentation.my_appointments_screen.MyAppointmentsViewModel
import com.example.fon_classroommanagment_frontend.screens.Appointment_Insertion_Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController=rememberNavController()
    //Aditional_Info_Screen()
    //TypeEMP_EducationEMP_Screen()
    val    requestViewMode: MyAppointmentsViewModel = hiltViewModel()

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
        composable(route = Screen.AdminRequestsScreen.route+"/{id}",
            arguments = listOf(navArgument("id"){
                type= NavType.LongType
            })
            ){
           val id=it.arguments?.getLong("id")

            if (id != null) {
                AdminsRequestScreen(navController,id)
            }
        }
//        composable(route = Screen.AditionalInfoScreen.route)
//        {
//
//            val registerObject= navController.previousBackStackEntry?.arguments?.getParcelable<UserRegistrationDTO>("registerObject")
//            if (registerObject != null) {
//                Aditional_Info_Screen(navController,registerObject)
//            }
//        }
//        composable(route = Screen.TypeEMPEducationEMPScreen.route){
//            val registerObject= navController.previousBackStackEntry?.arguments?.getParcelable<UserRegistrationDTO>("registerObject")
//
//            if (registerObject != null) {
//                TypeEMP_EducationEMP_Screen(registerObject, navHostController = navController)
//            }
//        }

        composable(route= Screen.DetailsClassroomScreen.route+"?classroomId={classroomId}",
        arguments = listOf(navArgument("classroomId"){
            type= NavType.LongType
        })){
            val classroomId=it.arguments?.getLong("classroomId")

            if (classroomId != null) {
                Details_Classroom_Screen(navController,classroomId)
            }
        }


        composable(route= Screen.AppointmentScreen.route+"?classroomId={classroomId}&name={name}&appointmentId={appointmentId}",
        arguments = listOf(navArgument("classroomId"){
            type= NavType.LongType
            defaultValue=-1L
                                                     },
            navArgument("name"){
                type=NavType.StringType
                nullable=true
                defaultValue=null
            },
            navArgument("appointmentId"){
                type=NavType.StringType
                nullable=true
                defaultValue=null
            },


        )){

            BackHandler(true) {
                navController.navigate(Screen.MyClassroomRequestsScreen.route)
            }

            var classroomChipDTO:ClassroomChipDTO?=null
            val classroomId=it.arguments?.getLong("classroomId")
            val appointmentId=it.arguments?.getString("appointmentId")
            val name=it.arguments?.getString("name")
            if(classroomId!=null && classroomId!=-1L && name!=null) classroomChipDTO= ClassroomChipDTO(classroomId,name)




                Appointment_Insertion_Screen(appointmentId,classroomChipDTO,navController)



             }



        composable(route= Screen.MyClassroomRequestsScreen.route

            ){
            BackHandler(true) {
            navController.navigate(Screen.BottomBarScreens.ReservationScreen.route)
            }

//            val registerObject= navController.previousBackStackEntry?.arguments?.getParcelable<RequestReservastion>("RequestReservastion")
//            val saved=navController.previousBackStackEntry?.arguments?.getBoolean("saved")
//           // Log.i("cao",saved.toString())

            My_Appointments_Screen(navController,requestViewMode)
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