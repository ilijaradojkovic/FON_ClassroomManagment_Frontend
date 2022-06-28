package com.example.fon_classroommanagment_frontend.domain.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.google.gson.Gson

internal class UserDetailsNavType : NavType<UserDetailsDTO>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UserDetailsDTO? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): UserDetailsDTO {
        return Gson().fromJson(value, UserDetailsDTO::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: UserDetailsDTO) {
        bundle.putParcelable(key, value)
    }
}
