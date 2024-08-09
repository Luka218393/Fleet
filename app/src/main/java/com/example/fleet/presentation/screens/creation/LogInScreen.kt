package com.example.fleet.presentation.screens.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.navigation.MainNavigation
import com.example.fleet.domain.viewModels.CreationViewModel
import com.example.fleet.domain.viewModels.CreationViewModelFactory
import com.example.fleet.presentation.components.input_fields.UnderlinedInputField
import com.example.fleet.presentation.components.scaffold_elements.SimpleTopBar

class LogInScreen : Screen {

    @Transient
    private val viewModel: CreationViewModel = ViewModelProvider(FleetApplication.viewModelStore, CreationViewModelFactory())[CreationViewModel::class.java]
    @Composable
    override fun Content() {

        val nav = LocalNavigator.current
        Scaffold(
            topBar = { SimpleTopBar(text = "Log in", onClick = { MainNavigation.pop(nav) })}
        ){ padding ->

            Column (
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                UnderlinedInputField(
                    value = viewModel.email,
                    placeholder = "E-mail",
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.displaySmall
                ) {
                    viewModel.email.value = it
                }
                Spacer(Modifier.height(32.dp))
                UnderlinedInputField(
                    value = viewModel.password,
                    placeholder = "Password",
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.displaySmall

                ) {
                    viewModel.password.value = it
                }
                Spacer(Modifier.height(32.dp))

                Row{
                    Button(
                        onClick = {
                            MainNavigation.goTo(Screens.ADDRESS_SELECTION, nav)
                        }
                    ) {
                        Text(text = "Sign in")
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    Button(
                        onClick = {
                            viewModel.openSettings()
                            MainNavigation.goTo(Screens.SETTINGS, nav)
                        }
                    ) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    }
}

