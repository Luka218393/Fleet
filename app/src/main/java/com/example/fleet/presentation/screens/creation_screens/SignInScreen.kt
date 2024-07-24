package com.example.fleet.presentation.screens.creation_screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.CreationViewModel
import com.example.fleet.domain.viewModels.CreationViewModelFactory
import com.example.fleet.presentation.fragments.input_fields.UnderlinedInputField

class SignInScreen: Screen {

    @Transient
    private val viewModel: CreationViewModel = ViewModelProvider(FleetApplication.viewModelStore, CreationViewModelFactory())[CreationViewModel::class.java]

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {}
        ){ padding ->

            Column (
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ){
                UnderlinedInputField(
                    value = viewModel.buildingId,
                    placeholder = "Enter building code",
                    maxLines = 1,
                    keyboardType = KeyboardType.Number,
                ) {
                    try {
                        viewModel.buildingId.value = it
                    }
                    catch (e: Error){
                        Log.i("SignInScreen","pokemoni")
                    }
                }
            }
        }
    }
}







