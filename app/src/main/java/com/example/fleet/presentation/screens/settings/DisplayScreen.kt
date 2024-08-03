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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.components.input_fields.AttributeDisplay
import com.example.fleet.presentation.components.input_fields.EditableTextField
import com.example.fleet.presentation.components.scaffold_elements.BottomBar
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton

//Todo create enum class that differentiates between displayed objects
//Todo change text styles
//Todo add typechange to be able to differrentiate between tenant, apartment and building ids <T>
data class DisplayScreen(
    @Transient
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val tenantId: String = "",
    @Transient
    private val apartmentId: String = "",
    @Transient
    private val buildingId: String = ""
): Screen {

    @Composable
    override fun Content() {
        var editMode by remember { mutableStateOf(false) }
        Scaffold(
            bottomBar = { BottomBar() },
            floatingActionButton = {
                SimpleFloatingButton(
                    onclick = {
                        if (!editMode) editMode = true
                        else {
                            //viewModel.changeTenant()
                            editMode = !editMode
                        }
                    },
                    icon = Icons.Default.Create
                )
            }
        ) { padding ->
            if (tenantId != "") {
                Box(modifier = Modifier.padding(padding)) {
                    DisplayTenant(tenantId, editMode, viewModel, modifier)
                }
            }
            else if(apartmentId != ""){
                Box(modifier = Modifier.padding(padding)) {
                    DisplayApartment(apartmentId, editMode, viewModel, modifier)
                }
            }

            else if(buildingId != ""){
                Box(modifier = Modifier.padding(padding)) {
                    DisplayTenant(tenantId, editMode, viewModel, modifier)
                }
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
        TopDisplay(
            value1 = viewModel.name,
            onVal1Change = {viewModel.name.value = it},
            value2 = viewModel.surname,
            onVal2Change = {viewModel.surname.value = it},
            editMode = editMode,
            profileImageRes = viewModel.profileImageRes.value
        )
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



@Composable
private fun DisplayApartment(
    apartmentId: String,
    editMode: Boolean,
    viewModel: DisplayViewModel,
    modifier: Modifier
) {
    LaunchedEffect(key1 = apartmentId) {
        viewModel.getApartmentAttributes(apartmentId = apartmentId)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopDisplay(
            value1 = viewModel.door,
            onVal1Change = {viewModel.door.value = it},
            value2 = viewModel.floor,
            onVal2Change = {viewModel.floor.value = it},
            editMode = editMode
        )

        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .verticalScroll(scrollState),
        ) {
            //Todo nota all attributes should be changable
            AttributeDisplay(attribute = "Maximal capacity", value = viewModel.maxCapacity, editMode = editMode, maxLines = 1, keyboardType = KeyboardType.Number){viewModel.maxCapacity.value = it}
            AttributeDisplay(attribute = "Area (meters)", value = viewModel.areaInMeters2, editMode = editMode, keyboardType = KeyboardType.Number){viewModel.areaInMeters2.value = it}
            AttributeDisplay(attribute = "Apartment number", value = viewModel.apartmentId, editMode = editMode, keyboardType = KeyboardType.Number){viewModel.apartmentId.value = it}//Todo fix this
            AttributeDisplay(attribute = "Number of Rooms", value = viewModel.numberOfRooms, editMode = editMode, keyboardType = KeyboardType.Number){viewModel.numberOfRooms.value = it}
            AttributeDisplay(attribute = "has pets", value = viewModel.hasPets, editMode = editMode){viewModel.hasPets.value = false}//Todo fix this


        }
    }
}

//Todo fix this to be more accurate
@Composable
fun <T> TopDisplay(

    value1: State<T>,
    onVal1Change:(String)->Unit,
    value2: State<T>,
    onVal2Change:(String)->Unit,

    editMode: Boolean,
    profileImageRes: Int? = null,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier.fillMaxHeight(0.2f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Todo make image cropable and fit this space (all images should have same dimensions)
        if (profileImageRes != null) {
            Image(
                painter = painterResource(id = profileImageRes),
                contentDescription = " ",//Todo add description
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
            )
            Spacer(modifier = modifier.width(20.dp))
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxHeight()
                .weight(3f)
        ) {
            EditableTextField(
                editMode,
                value = value1,
                placeholder = "Add placeholder",
                textStyle = MaterialTheme.typography.displaySmall
            )
            {
                onVal1Change(it)
            }

            EditableTextField(
                editMode = editMode,
                value = value2,
                "Add placeholder",
                textStyle = MaterialTheme.typography.displaySmall
            ) {
                onVal2Change(it)
            }
        }


    }
    HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)
}