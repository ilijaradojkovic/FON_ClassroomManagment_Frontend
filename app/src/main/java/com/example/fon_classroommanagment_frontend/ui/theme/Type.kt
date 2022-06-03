package com.example.fon_classroommanagment_frontend.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fon_classroommanagment_frontend.R

// Set of Material typography styles to start with
val merriweather=FontFamily(Font(R.font.merriweather_regular),Font(R.font.merriweather_light),Font(R.font.merriweather_bold))
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
,

    bodyLarge = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),

    displayMedium = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
            displayLarge = TextStyle(
            fontFamily = merriweather,
    fontWeight = FontWeight.Bold,
    fontSize = 48.sp
),
    headlineSmall = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Bold,
        fontSize = 31.sp
    ),
    titleMedium = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = merriweather,
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp
    ),

    )