package com.example.fleet.presentation.screens.settings.display

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.components.input_fields.AttributeDisplay
import com.example.fleet.presentation.components.input_fields.EditableTextField
import com.example.fleet.presentation.components.input_fields.castInputToInt
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton

data class DisplayTenant(
    @Transient
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val tenantId: String = "",

): Screen {

    override val key: ScreenKey
        get() = Screens.DISPLAY_TENANT.key

    @Composable
    override fun Content() {
        var editMode by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = tenantId) {
            editMode = false
        }
        Scaffold(
            floatingActionButton = {
                SimpleFloatingButton(
                    onclick = {
                        //Todo make button enabled only if all fields are filled
                        if (!editMode) editMode = true
                        viewModel.changeTenant()
                        editMode = !editMode
                    },
                    icon = Icons.Default.Create
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                DisplayTenant(tenantId, editMode, viewModel, modifier)
            }
        }
    }
}


@Composable
private fun DisplayTenant(
    tenantId: String,
    editMode: Boolean,
    viewModel: DisplayViewModel,
    modifier: Modifier
) {
    LaunchedEffect(key1 = tenantId) {
        viewModel.getTenantAttributes(tenantId)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier.fillMaxHeight(0.2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Todo make image cropable and fit this space (all images should have same dimensions)
            Image(
                painter = painterResource(id = viewModel.profileImageRes.value ?: R.drawable.account_icon),
                contentDescription = "Profile image of ${viewModel.name.value} ${viewModel.surname.value}",//Todo add description
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
            )

            Spacer(modifier = modifier.width(20.dp))

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxHeight()
                    .weight(3f)
            ) {
                EditableTextField(
                    editMode,
                    value = viewModel.name,
                    placeholder = "Name",
                    textStyle = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center
                ) {
                    viewModel.name.value = it
                }

                EditableTextField(
                    editMode = editMode,
                    value = viewModel.surname,
                    "Surname",
                    textStyle = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center
                ) {
                    viewModel.surname.value = it
                }
            }


        }

        HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            //Todo nota all attributes should be changable
            //Todo add inputs for Date and predeclared list (ApartmentId)
            AttributeDisplay(attribute = "About me", value = viewModel.description, editMode = editMode, maxLines = 4){viewModel.description.value = it}
            AttributeDisplay(attribute = "Profession", value = viewModel.profession, editMode = editMode){viewModel.profession.value = it}
            AttributeDisplay(attribute = "Apartment number", value = viewModel.apartmentId, editMode = false){viewModel.apartmentId.value = it}//Todo fix this
            AttributeDisplay(attribute = "Birthday", value = viewModel.birthday, editMode = editMode, onDateValueChange = {viewModel.birthday.value = it})
            AttributeDisplay(attribute = "E-mail", value = viewModel.email, editMode = editMode){viewModel.email.value = it}
            AttributeDisplay(attribute = "Phone number", value = viewModel.phoneNumber, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.phoneNumber, it) }


            //Todo add contact button
            AttributeDisplay(attribute = "Joined", value = viewModel.joinedDate, editMode = false, onDateValueChange = {viewModel.joinedDate.value = it})
        }
    }
}
