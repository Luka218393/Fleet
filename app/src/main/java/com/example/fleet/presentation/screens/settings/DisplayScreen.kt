package com.example.fleet.presentation.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.data.tenants
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.components.input_fields.AttributeDisplay
import com.example.fleet.presentation.components.input_fields.EditableTextField
import com.example.fleet.presentation.components.input_fields.castInputToInt
import com.example.fleet.presentation.components.scaffold_elements.BottomBar
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton

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
        LaunchedEffect(key1 = tenantId) {
            editMode = false
        }
        Scaffold(
            bottomBar = { BottomBar() },
            floatingActionButton = {
                SimpleFloatingButton(
                    onclick = {
                        if (!editMode) editMode = true
                        else {
                            if (tenantId != "") {
                                viewModel.changeTenant()
                            }
                            else if(apartmentId != ""){
                                viewModel.changeApartment()
                            }
                            else if(buildingId != ""){
                                viewModel.changeBuilding()
                            }
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
                    DisplayBuilding(buildingId, editMode, viewModel, modifier)
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
            AttributeDisplay(attribute = "Phone number", value = viewModel.phoneNumber, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.phoneNumber, it)}


            //Todo add contact button
            AttributeDisplay(attribute = "Joined", value = viewModel.joinedDate, editMode = false, onDateValueChange = {viewModel.joinedDate.value = it})
        }
    }
}


//Todo display apartment
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
    ){
        Row(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .horizontalScroll(rememberScrollState()),
        ){
            for (tenant in viewModel.apartmentsTenants.value){
                TenantButton(imageRes = tenant.profileImageRes, tenantId = tenant.id, name = tenant.name +  " " + tenant.surname)
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
            AttributeDisplay(attribute = "Floor", value = viewModel.floor, editMode = editMode, maxLines = 1, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.floor, it) }
            AttributeDisplay(attribute = "Door", value = viewModel.door, editMode = editMode, maxLines = 1){viewModel.door.value = it}
            AttributeDisplay(attribute = "Maximal capacity", value = viewModel.maxCapacity, editMode = editMode, maxLines = 1, keyboardType = KeyboardType.Number){castInputToInt(viewModel.maxCapacity, it)}
            AttributeDisplay(attribute = "Area (meters)", value = viewModel.areaInMeters2, editMode = editMode, keyboardType = KeyboardType.Number){castInputToInt(viewModel.areaInMeters2, it)}
            AttributeDisplay(attribute = "Number of Rooms", value = viewModel.numberOfRooms, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.numberOfRooms, it)}
            AttributeDisplay(attribute = "Has pets", value = viewModel.hasPets, editMode = editMode, onBooleanValueChange = {viewModel.hasPets.value = it})


        }
    }
}


@Composable
private fun DisplayBuilding(
    buildingId: String,
    editMode: Boolean,
    viewModel: DisplayViewModel,
    modifier: Modifier
) {
    LaunchedEffect(key1 = buildingId) {
        viewModel.getBuildingAttributes(buildingId = buildingId)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight(0.2f)
        ) {
            EditableTextField(
                editMode,
                value = viewModel.city,
                placeholder = "Add placeholder",
                textStyle = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            ) {
                viewModel.city.value = it
            }

            EditableTextField(
                editMode = editMode,
                value = viewModel.address,
                "Add placeholder",
                textStyle = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            ) {
                viewModel.address.value = it
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
            AttributeDisplay(attribute = "Country", value = viewModel.country, editMode = editMode, keyboardType = KeyboardType.Number){viewModel.country.value = it}
            AttributeDisplay(attribute = "Region", value = viewModel.region, editMode = editMode, keyboardType = KeyboardType.Number){viewModel.region.value = it}
            AttributeDisplay(attribute = "Number of floors", value = viewModel.floors, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.floors, it)  }//Todo fix this
            AttributeDisplay(attribute = "Number of apartments", value = viewModel.numberOfApartments, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.numberOfApartments, it) }
            AttributeDisplay(attribute = "Creation Year", value = viewModel.creationYear, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.creationYear, it)}//Todo fix this
        }
    }
}



@Composable
fun TenantButton(
    imageRes: Int,
    tenantId: String,
    name: String
){
    val nav = LocalNavigator.current

    Column(
        Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        FilledIconButton(
            onClick = {
                Navigation.goTo(Screens.DISPLAY_TENANT, nav, tenantId)
            },
            modifier = Modifier
                .weight(5f)
                .size(120.dp),
            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                Modifier.fillMaxSize()
            )
        }
        Text(
            name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}



@Preview
@Composable
fun TenantButtonPreview(){
    TenantButton(imageRes = tenants[0].profileImageRes, tenantId = tenants[0].id, name = tenants[0].name)
}
