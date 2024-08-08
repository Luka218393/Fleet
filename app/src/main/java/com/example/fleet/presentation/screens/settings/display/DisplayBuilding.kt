package com.example.fleet.presentation.screens.settings.display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.data.tenants
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.components.input_fields.AttributeDisplay
import com.example.fleet.presentation.components.input_fields.EditableTextField
import com.example.fleet.presentation.components.input_fields.castInputToInt
import com.example.fleet.presentation.components.scaffold_elements.NavigationBottomBar
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton

data class DisplayBuilding(
    @Transient
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val buildingId: String = ""
): Screen {

    @Composable
    override fun Content() {
        var editMode by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = buildingId) {
            editMode = false
        }
        Scaffold(
            bottomBar = { NavigationBottomBar() },
            floatingActionButton = {
                SimpleFloatingButton(
                    onclick = {
                        //Todo make button enabled only if all fields are filled
                        if (!editMode) editMode = true
                        viewModel.changeBuilding()

                        editMode = !editMode
                    },
                    icon = Icons.Default.Create
                )
            }
        ) { padding ->

            Box(modifier = Modifier.padding(padding)) {
                DisplayBuilding(buildingId, editMode, viewModel, modifier)
            }

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





@Preview
@Composable
fun TenantButtonPreview(){
    TenantButton(imageRes = tenants[0].profileImageRes, tenantId = tenants[0].id, name = tenants[0].name)
}
