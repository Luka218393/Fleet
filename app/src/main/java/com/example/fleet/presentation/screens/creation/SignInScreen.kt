package com.example.fleet.presentation.screens.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.navigation.MainNavigation
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.viewModels.CreationViewModel
import com.example.fleet.domain.viewModels.CreationViewModelFactory
import com.example.fleet.presentation.components.input_fields.UnderlinedInputField
import com.example.fleet.presentation.components.scaffold_elements.SimpleTopBar


class SignInScreen: Screen {

    @Transient
    private val viewModel: CreationViewModel = ViewModelProvider(FleetApplication.viewModelStore, CreationViewModelFactory())[CreationViewModel::class.java]

    override val key: ScreenKey
        get() = Screens.SIGN_IN.key

    @Composable
    override fun Content() {
        val nav = LocalNavigator.current
        Scaffold(
            topBar = { SimpleTopBar(text = "Sign in", onClick = { MainNavigation.pop(nav) }) }
        ){ padding ->

            Column (
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                SignInFields(viewModel = viewModel)

                Spacer(Modifier.height(32.dp))

                Button(
                    enabled = viewModel.areFieldsFilled(),
                    onClick = {
                        MainNavigation.goTo(Screens.SETTINGS, nav)
                        viewModel.createTenant()
                    }
                ) {
                    Text(text = "Continue")
                }
            }
        }
    }
}

@Composable
fun SignInFields(
    viewModel: CreationViewModel
){
    UnderlinedInputField(
        value = viewModel.name,
        placeholder = "Name",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.name.value = it
    }

    Spacer(Modifier.height(32.dp))
    UnderlinedInputField(
        value = viewModel.surname,
        placeholder = "Surname",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall

    ) {
        viewModel.surname.value = it
    }

    Spacer(Modifier.height(32.dp))
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
        value = viewModel.phoneNumber,
        placeholder = "Phone number",
        maxLines = 1,
        keyboardType = KeyboardType.Number,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.phoneNumber.value = it
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
    UnderlinedInputField(
        value = viewModel.repeatedPassword,
        placeholder = "Repeat password",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.repeatedPassword.value = it
    }

    Spacer(Modifier.height(32.dp))
    UnderlinedInputField(
        value = viewModel.profession,
        placeholder = "Profession",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.profession.value = it
    }

    Spacer(Modifier.height(32.dp))
    UnderlinedInputField(
        value = viewModel.description,
        placeholder = "Description",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.description.value = it
    }

    Spacer(Modifier.height(32.dp))
    UnderlinedInputField(
        value = viewModel.gender,
        placeholder = "Gender",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.gender.value = it
    }

    Spacer(Modifier.height(32.dp))
    UnderlinedInputField(
        value = viewModel.birthday,
        placeholder = "Birthday",
        maxLines = 1,
        textStyle = MaterialTheme.typography.displaySmall
    ) {
        viewModel.birthday.value = it
    }
}