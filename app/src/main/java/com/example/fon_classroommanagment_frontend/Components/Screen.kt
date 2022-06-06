package com.example.fon_classroommanagment_frontend.Components

import androidx.annotation.DrawableRes
import com.example.fon_classroommanagment_frontend.R

sealed class Screen(val route:String) {

    object LoginScreen:Screen("Login_Screen")
    object RegisterScreen:Screen("Register_Screen")
    object MainScreen:Screen("Main_Screen")
    object DetailsClassroomScreen:Screen("DetailsClassroom_Screen")
    object AppointmentScreen:Screen("Appointment_Screen")
    sealed class BottomBarScreens(  route:String,@DrawableRes val icon:Int,@DrawableRes val iconFilled:Int,val title:String):Screen(route){
        object  AllClassroomsScreen:BottomBarScreens("AllClassrooms_Screen", R.drawable.classrooms,R.drawable.classrooms_filled,"Classrooms")
        object ReservationScreen:BottomBarScreens("Reservation_Screen",R.drawable.callendar,R.drawable.calendar_filled ,"Reservations")
        object  UserProfileScreen:BottomBarScreens("UserProfile_Screen",R.drawable.avatar,R.drawable.avatar_filled,"Profile")
    }
}