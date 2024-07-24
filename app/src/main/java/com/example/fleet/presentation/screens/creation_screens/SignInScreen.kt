package com.example.fleet.presentation.screens.creation_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.CreationViewModel
import com.example.fleet.domain.viewModels.CreationViewModelFactory

class SignInScreen: Screen {

    @Transient
    private val viewModel: CreationViewModel = ViewModelProvider(FleetApplication.viewModelStore, CreationViewModelFactory())[CreationViewModel::class.java]

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {}
        ){ padding ->

            Box(
                modifier = Modifier.padding(padding)
                    .fillMaxSize()
                    .clickable {  },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "This part of the app still isn't finished",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}