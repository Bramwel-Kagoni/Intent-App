package com.bramwel.intentapp.ui.theme.screens.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationBarItems(val icon : ImageVector){
    Person(icon = Icons.Default.Person),
    Call(icon = Icons.Default.Call),
    Settings(icon = Icons.Default.Settings)
}