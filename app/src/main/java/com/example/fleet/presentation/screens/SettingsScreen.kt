package com.example.fleet.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.SettingsViewModel
import com.example.fleet.domain.viewModels.SettingsViewModelFactory
import com.example.fleet.presentation.settings_fragments.ColorSelector


//Todo add scroll
//Todo give this normal icons
class SettingsScreen (
) : BaseScreen(){
    private val viewModel: SettingsViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, SettingsViewModelFactory())[SettingsViewModel::class.java]
    @Composable
    override fun InnerContent() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SettingsSeparator(text = "Theme")
            SettingsBar(iconVector = Icons.Default.Create, text = "Color",onClick = {viewModel.toggleColorPalette()})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Animations",onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Font size",onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Theme",onClick = {})

            SettingsSeparator(text = "Help the dev")
            SettingsBar(iconVector = Icons.Default.Settings, text = "Message the dev", onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Answer a poll", onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Get resources", onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Report problem", onClick = {})

            SettingsSeparator(text = "Edit")
            SettingsBar(iconVector = Icons.Default.Settings, text = "Edit account",onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Edit apartment",onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Edit building",onClick = {})

            SettingsSeparator(text = "Acconut")
            SettingsBar(iconVector = Icons.Default.Settings, text = "Terms of service",onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Change account",onClick = {})
            SettingsBar(iconVector = Icons.Default.Settings, text = "Privacy",onClick = {})


            if (viewModel.showColorSelector){
                ColorSelector(
                    onConfirm = {viewModel.changeSettingsColor(it);viewModel.toggleColorPalette()},
                    onDismiss = {viewModel.toggleColorPalette()},
                )
            }
        }
    }
}

@Composable
fun SettingsBar(
    modifier: Modifier = Modifier,
    iconVector: ImageVector,
    text: String,
    onClick: () -> Unit
){

    Row(
        modifier
            .clickable { onClick() }
            .padding(8.dp)
            .width(300.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = iconVector,
                contentDescription = text,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = modifier
                    .size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "More",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = modifier
                .size(32.dp)
        )
    }
}

@Composable
fun SettingsSeparator(
    text: String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        HorizontalDivider(modifier = Modifier.weight(2f))
        Text(
            text = text ,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color(255,255,255)
        )
        HorizontalDivider(modifier = Modifier.weight(2f))
    }
}


@Preview
@Composable
fun SettingsBarPreview(){
    SettingsBar(
        iconVector = Icons.Default.Check,
        text = "More settings",
        onClick = {}
    )
}