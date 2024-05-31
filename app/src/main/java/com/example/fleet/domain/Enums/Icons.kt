package com.example.fleet.domain.Enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

enum class Icono(val imageVector: ImageVector, val nomen: String) {
    ADD(Icons.Filled.Add, "Filled.Add"),
    ARROW_BACK(Icons.Filled.ArrowBack, "Filled.ArrowBack"),
    ARROW_FORWARD(Icons.Filled.ArrowForward, "Filled.ArrowForward"),
    CHECK(Icons.Filled.Check, "Filled.Check"),
    CLOSE(Icons.Filled.Close, "Filled.Close"),
    DELETE(Icons.Filled.Delete, "Filled.Delete"),
    EDIT(Icons.Filled.Edit, "Filled.Edit"),
    FAVORITE(Icons.Filled.Favorite, "Filled.Favorite"),
    HOME(Icons.Filled.Home, "Filled.Home"),
    INFO(Icons.Filled.Info, "Filled.Info"),
    MENU(Icons.Filled.Menu, "Filled.Menu"),
    SETTINGS(Icons.Filled.Settings, "Filled.Settings"),
    SHARE(Icons.Filled.Share, "Filled.Share")
}