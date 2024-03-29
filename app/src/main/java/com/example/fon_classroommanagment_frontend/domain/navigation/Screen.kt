package com.example.fon_classroommanagment_frontend.domain.navigation

import androidx.annotation.DrawableRes
import com.example.fon_classroommanagment_frontend.R

sealed class Screen(val route:String) {

    object SplashScreen: Screen("Splash_Screen")
    object LoginScreen: Screen("Login_Screen")
    object RegisterScreen: Screen("Register_Screen")
    object MainScreen: Screen("Main_Screen")
    object DetailsClassroomScreen: Screen("Details_Classroom_Screen")
    object AppointmentScreen: Screen("Appointment_Screen")
//    object AditionalInfoScreen: Screen("Aditional_Info_Screen")
//    object TypeEMPEducationEMPScreen: Screen("TypeEMP_EducationEMP_Screen")
    object MyClassroomRequestsScreen: Screen("My_ClassroomRequests_Screen")
    object AdminRequestsScreen: Screen("Admin_Requets_Screen")
    object DetailsAppointmentScreen: Screen("Details_Appointment_Screen")



    sealed class BottomBarScreens(  route:String,@DrawableRes val icon:Int,@DrawableRes val iconFilled:Int,val title:String):
        Screen(route){
        object  AllClassroomsScreen: BottomBarScreens("AllClassrooms_Screen", R.drawable.classrooms,R.drawable.classrooms_filled,"Classrooms")
        object ReservationScreen:
            BottomBarScreens("Reservation_Screen",R.drawable.callendar,R.drawable.calendar_filled ,"Reservations")
        object  UserProfileScreen:
            BottomBarScreens("UserProfile_Screen",R.drawable.user_default_light,R.drawable.avatar_filled,"Profile")
    }
}