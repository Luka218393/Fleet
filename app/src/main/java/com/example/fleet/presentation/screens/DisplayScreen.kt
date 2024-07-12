package com.example.fleet.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.viewModels.DisplayViewModel
import com.example.fleet.domain.viewModels.DisplayViewModelFactory
import com.example.fleet.presentation.fragments.UnderlinedInputField
import com.example.fleet.presentation.fragments.UnderlinedInputField_Int
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.SimpleFloatingButton

//Todo change text styles
data class DisplayScreen(
    @Transient
    private val viewModel: DisplayViewModel = ViewModelProvider(FleetApplication.viewModelStore, DisplayViewModelFactory())[DisplayViewModel::class.java],
    @Transient
    private val modifier: Modifier = Modifier,
    @Transient
    private val tenantId: Int
): Screen {

    @Composable
    override fun Content() {
        var editMode by remember { mutableStateOf(false) }

        Scaffold(
            bottomBar = {BottomBar()},
            floatingActionButton = { SimpleFloatingButton(onclick = { editMode = !editMode }, icon = Icons.Default.Create)}
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                DisplayTenant(tenantId, editMode)
            }
        }
    }


    @Composable
    private fun DisplayTenant(
        tenantId: Int,
        editMode: Boolean
    ) {
        //viewModel.getTenantById(tenantId)
        val tenant = viewModel.getTenantById(tenantId)//viewModel.displayTenant.collectAsState(null).value
        if (tenant != null) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxHeight(0.2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Todo make image cropable and fit this space (all images should have sam dimensions)
                    Image(
                        painter = painterResource(id = tenant.profileImageRes ?: R.drawable.lukinaikona),
                        contentDescription = tenant.name,
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier
                            .fillMaxHeight()
                            .weight(3f)

                    ) {
                        //Todo add remaining fields
                        EditableTextField( editMode = editMode, value = remember { mutableStateOf(tenant.name) }, "Name", textStyle = MaterialTheme.typography.displaySmall)

                        EditableTextField( editMode = editMode, value = remember { mutableStateOf(tenant.surname)}, "Surname", textStyle = MaterialTheme.typography.displaySmall)

                        EditableTextField( editMode = editMode, value = remember { mutableStateOf(tenant.age.toString())},"Age", textStyle = MaterialTheme.typography.displaySmall )


                    }
                }
                HorizontalDivider(modifier.padding(8.dp), thickness = 2.dp)
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    AttributeDisplay(attribute = "About me", value = remember { mutableStateOf(tenant.aboutMe.toString())}, editMode = editMode)
                    AttributeDisplay(attribute = "Profession", value = remember { mutableStateOf(tenant.profession.toString())}, editMode = editMode)
                    AttributeDisplay(attribute = "Apartment number", value = remember { mutableStateOf(tenant.apartmentId.toString())}, editMode = editMode)
                    AttributeDisplay(attribute = "Birthday", value = remember { mutableStateOf(tenant.birthday.toString())}, editMode = editMode)
                    AttributeDisplay(attribute = "E-mail", value = remember { mutableStateOf(tenant.email.toString())}, editMode = editMode)
                    AttributeDisplay(attribute = "Phone number", value = remember { mutableStateOf(tenant.phoneNumber.toString())}, editMode = editMode)
                    AttributeDisplay(attribute = "Joined", value = remember { mutableStateOf(tenant.createdAt.toString())}, editMode = editMode)
                }
            }
        }
    }
}

@Composable
fun AttributeDisplay(
    attribute: String,
    value: MutableState<String>,
    editMode: Boolean,
){
    Column(
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = attribute,
            style = MaterialTheme.typography.titleMedium
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ){
            EditableTextField(editMode = editMode, value = value, placeholder = attribute)
        }
    }
}






//Todo make text color not change on edit
@Composable
fun EditableTextField(
    editMode: Boolean,
    value: MutableState<String>,
    placeholder: String,
    maxLines: Int = 1,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
){
    if (editMode) {
        UnderlinedInputField(value = value, placeholder = placeholder, maxLines = maxLines, textStyle = textStyle)
    } else {
        Text(
            text = value.value,
            style = textStyle
        )
    }
}

//Todo this doesn't work
@Composable
fun EditableTextField_Int(
    editMode: Boolean,
    value: MutableState<Int>,
    placeholder: String,
    maxLines: Int = 1
){
    if (editMode) {
        UnderlinedInputField_Int(value = value, placeholder = placeholder, maxLines = maxLines)
    } else {
        Text(
            text = value.value.toString(),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun TenantDisplayScreen(){
    DisplayScreen(tenantId = 7).Content()
}