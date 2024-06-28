package com.example.fleet.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory

data class DisplayScreen(
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    private val modifier: Modifier = Modifier
): BaseScreen() {

    @Composable
    override fun InnerContent() {
        DisplayTenant(2)
    }

    @Composable
    private fun DisplayTenant(
        tenantId: Int
    ) {
        viewModel.getTenantById(tenantId)
        val tenant = viewModel.displayTenant.collectAsState(null).value
        var editMode by remember { mutableStateOf(true) }
        if (tenant != null) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Row(
                    modifier = modifier.height(200.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            id = tenant?.profileImageRes ?: R.drawable.lukinaikona
                        ), contentDescription = tenant?.name
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = modifier.fillMaxHeight()

                    ) {
                        //Todo add remaining fields
                        EditableTextField( editMode = editMode, display = remember { mutableStateOf(tenant.name) }, "Name")

                        EditableTextField( editMode = editMode, display = remember { mutableStateOf(tenant.name)}, "Surname")

                        EditableTextField( editMode = editMode, display = remember { mutableStateOf(tenant.age.toString())},"Age" )

                        EditableTextField( editMode = editMode, display = remember { mutableStateOf(tenant.gender.toString())} ,"Gender" )

                    }
                }
                HorizontalDivider()
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Add tags for isOnline, isApartmanet headm is tenenatLeader"
                    )

                    Text(
                        text = tenant.apartmentId.toString()
                    )
                    Text(
                        text = tenant.phoneNumber ?: "No phone number"
                    )
                    Text(
                        text = tenant.email ?: "no email"
                    )
                    Text(
                        text = tenant.birthday.toString()
                    )
                    Text(
                        text = tenant.profession ?: "No profession"
                    )
                    Text(
                        text = tenant.aboutMe ?: "No self description"
                    )
                    Text(
                        text = tenant.createdAt.toString()
                    )

                    Button(onClick = { editMode = !editMode}) {
                        Text(text = "ASASDASDSDAF")
                    }
                }
            }
        }
    }
}
@Composable
fun EditableTextField(
    editMode: Boolean,
    display: MutableState<Any>,
    label: String
){
    if (editMode) {
        BasicTextField(
            value = display.value.toString(),
            onValueChange = { display.value = it },
            singleLine = true,
            modifier = Modifier.height(30.dp),
            decorationBox = { innerTextField ->
                Column(
                    Modifier.padding(3.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    innerTextField()
                    HorizontalDivider(
                        thickness = 2.dp
                    )
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary)
        )
    } else {
        Text(
            text = display.value.toString()
        )
    }
}

@Preview
@Composable
fun TenantDisplayScreen(){
    DisplayScreen().InnerContent()
}