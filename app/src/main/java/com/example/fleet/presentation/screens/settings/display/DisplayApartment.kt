package com.example.fleet.presentation.screens.settings.display

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.navigation.MainNavigation
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.components.input_fields.AttributeDisplay
import com.example.fleet.presentation.components.input_fields.castInputToInt
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton

data class DisplayApartment(
    @Transient
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val apartmentId: String = "",
): Screen {


    private val tag = "DisplayApartmentScreen"

    override val key: ScreenKey
        get() = Screens.DISPLAY_APARTMENT.key

    @Composable
    override fun Content() {
        var editMode by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = apartmentId) {
            editMode = false
        }
        Scaffold(
            floatingActionButton = {
                SimpleFloatingButton(
                    onclick = {
                        //Todo make button enabled only if all fields are filled
                        if (!editMode) editMode = true

                            else if(apartmentId != ""){
                                viewModel.changeApartment()
                            }

                            editMode = !editMode

                    },
                    icon = Icons.Default.Create
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                DisplayApartment(apartmentId, editMode, viewModel, modifier)
            }
        }
    }
}


//Todo display apartment
@Composable
internal fun DisplayApartment(
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
            AttributeDisplay(attribute = "Maximal capacity", value = viewModel.maxCapacity, editMode = editMode, maxLines = 1, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.maxCapacity, it) }
            AttributeDisplay(attribute = "Area (meters)", value = viewModel.areaInMeters2, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.areaInMeters2, it) }
            AttributeDisplay(attribute = "Number of Rooms", value = viewModel.numberOfRooms, editMode = editMode, keyboardType = KeyboardType.Number){ castInputToInt(viewModel.numberOfRooms, it) }
            AttributeDisplay(attribute = "Has pets", value = viewModel.hasPets, editMode = editMode, onBooleanValueChange = {viewModel.hasPets.value = it})


        }
    }
}



@Composable
internal fun TenantButton(
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
                MainNavigation.goTo(Screens.DISPLAY_TENANT, nav, tenantId)
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