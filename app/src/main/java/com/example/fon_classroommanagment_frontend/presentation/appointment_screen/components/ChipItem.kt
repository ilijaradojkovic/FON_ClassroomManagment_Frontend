package com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components

import com.bonbon.library.model.FilterableEntity

data class ChipItem(
    val value: String = "",
    val id: Int
) : FilterableEntity {
    override fun filter(query: String): Boolean {
        return value.startsWith(query, ignoreCase = true)
    }
}