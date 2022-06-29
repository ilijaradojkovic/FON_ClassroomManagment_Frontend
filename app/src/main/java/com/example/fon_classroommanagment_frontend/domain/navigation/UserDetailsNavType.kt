package com.example.fon_classroommanagment_frontend.domain.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.google.gson.Gson

 class ReservationDTONav : NavType<ReserveDTO>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): ReserveDTO? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ReserveDTO {
        return Gson().fromJson(value, ReserveDTO::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ReserveDTO) {
        bundle.putParcelable(key, value)
    }
}
