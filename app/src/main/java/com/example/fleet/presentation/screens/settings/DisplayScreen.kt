package com.example.fleet.presentation.screens.settings

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.components.input_fields.AttributeDisplay
import com.example.fleet.presentation.components.input_fields.EditableTextField
import com.example.fleet.presentation.components.scaffold_elements.BottomBar
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton

//Todo change text styles
//Todo add typechange to be able to differrentiate between tenant, apartment and building ids <T>
data class  DisplayScreen(
    @Transient
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val tenantId: String
): Screen {

    @Composable
    override fun Content() {
        var editMode by remember { mutableStateOf(false) }

        Scaffold(
            bottomBar = {BottomBar()},
            floatingActionButton = {
                SimpleFloatingButton(
                    onclick = {
                        if (!editMode) editMode = !editMode
                        else {
                            viewModel.changeTenant()
                            editMode = !editMode
                        }
                        },
                    icon = Icons.Default.Create)
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                DisplayTenant(tenantId, editMode)
            }
        }
    }


    @Composable
    private fun DisplayTenant(
        tenantId: String,
        editMode: Boolean
    ) {
        LaunchedEffect(key1 = tenantId) {
            viewModel.getTenantAttributes(tenantId)

        }
        //Todo add snackbars to int inputs
        val snackbarHostState = remember { SnackbarHostState() }

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = modifier.fillMaxHeight(0.2f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Todo make image cropable and fit this space (all images should have sam dimensions)
                Image(
                    painter = painterResource(id = viewModel.profileImageRes.value ?: R.drawable.lukinaikona),
                    contentDescription = viewModel.name.value,
                    modifier = Modifier.weight(2f)
                )
                Spacer(modifier = modifier.width(20.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxHeight()
                        .weight(3f)
                ) {
                    //Todo Make every tenants attribute changable
                    EditableTextField(
                        editMode,
                        value = viewModel.name,
                        placeholder = "Name",
                        textStyle = MaterialTheme.typography.displaySmall)
                    {
                        viewModel.name.value = it
                    }

                    EditableTextField( editMode = editMode, value = viewModel.surname, "Surname", textStyle = MaterialTheme.typography.displaySmall){
                        viewModel.surname.value = it
                    }

                }
            }
            HorizontalDivider(thickness = 2.dp)
            SnackbarHost(hostState = snackbarHostState)

            val scrollState = rememberScrollState()
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(scrollState),
            ) {
                //Todo nota all attributes should be changable
                //Todo add inputs for Date and predeclared list (ApartmentId)
                AttributeDisplay(attribute = "About me", value = viewModel.description, editMode = editMode, maxLines = 4){viewModel.description.value = it}
                AttributeDisplay(attribute = "Profession", value = viewModel.profession, editMode = editMode){viewModel.profession.value = it}
                AttributeDisplay(attribute = "Apartment number", value = viewModel.apartmentId, editMode = editMode){viewModel.apartmentId.value = it}//Todo fix this
                AttributeDisplay(attribute = "Birthday", value = viewModel.birthday, editMode = editMode){viewModel.birthday.value = it}
                AttributeDisplay(attribute = "E-mail", value = viewModel.email, editMode = editMode){viewModel.email.value = it}
                AttributeDisplay(attribute = "Phone number", value = viewModel.phoneNumber, editMode = editMode){viewModel.phoneNumber.value = it}

                //Todo add contact button
                ///AttributeDisplay(attribute = "Joined", value = viewModel., editMode = editMode)
            }
        }
    }
}

