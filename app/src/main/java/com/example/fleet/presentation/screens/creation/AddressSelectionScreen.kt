package com.example.fleet.presentation.screens.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.navigation.MainNavigation
import com.example.fleet.domain.viewModels.CreationViewModel
import com.example.fleet.domain.viewModels.CreationViewModelFactory
import com.example.fleet.presentation.components.input_fields.CustomListSelector
import com.example.fleet.presentation.components.input_fields.UnderlinedInputField
import com.example.fleet.presentation.components.scaffold_elements.SimpleTopBar

class AddressSelectionScreen: Screen {

    @Transient
    private val viewModel: CreationViewModel = ViewModelProvider(FleetApplication.viewModelStore, CreationViewModelFactory())[CreationViewModel::class.java]
    @Composable
    override fun Content() {

        val nav = LocalNavigator.current

        Scaffold(
            topBar = { SimpleTopBar(text = "Building", onClick = { MainNavigation.pop(nav) })}
        ){ padding ->

            Column (
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                UnderlinedInputField(
                    value = viewModel.buildingId,
                    placeholder = "Enter building code",
                    maxLines = 1,
                    keyboardType = KeyboardType.Number,
                    textStyle = MaterialTheme.typography.displaySmall
                ) {
                    viewModel.buildingId.value = it
                }
                Spacer(Modifier.height(32.dp))
                if (viewModel.buildingIdIsValid(viewModel.buildingId.value)) {
                    val apartments = viewModel.getBuildingsApartments()
                    if (apartments.isNotEmpty()) {
                        CustomListSelector(
                            width = 128.dp,
                            itemHeight = 40.dp,
                            items = apartments.map{"Floor ${it.floor}, door ${it.door}"},
                            textStyle = MaterialTheme.typography.bodyMedium,
                            textColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.secondary,
                            initialItem =  apartments.first()
                        ) { index, _ -> viewModel.apartmentId.value = apartments[index].id }
                    }
                }
                Spacer(Modifier.height(32.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(onClick = { MainNavigation.goTo(Screens.LOG_IN, nav) }) {
                        Text(text = "Log in")
                    }
                    Button(onClick = { MainNavigation.goTo(Screens.SIGN_IN, nav) }, enabled = viewModel.apartmentId.value.isNotEmpty()) {
                        Text(text = "Continue")
                        //Todo make db check if Id exists
                        // Todo make ids somehow shorter
                    }
                }
            }
        }
    }
}

