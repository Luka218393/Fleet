package com.example.fleet.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.SettingsViewModel
import com.example.fleet.domain.viewModels.SettingsViewModelFactory
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.settings_fragments.ColorSelector


//Todo add scroll
//Todo give this normal icons
class SettingsScreen  : Screen{
    @Transient
    private val viewModel: SettingsViewModel = ViewModelProvider(FleetApplication.viewModelStore, SettingsViewModelFactory())[SettingsViewModel::class.java]
    @Composable
    override fun Content() {
        val nav = LocalNavigator.current
        Scaffold(
            bottomBar = {BottomBar() },
        ) { padding ->
            val systemTheme = isSystemInDarkTheme()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(padding)
            ){
                SettingsSeparator(text = "Theme")
                SettingsBar(iconVector = Icons.Default.Create, text = "Color", onClick = {viewModel.toggleColorPalette()})
                SettingsBar(painterIcon = painterResource(id = R.drawable.animation_icon), text = "Animations", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(painterIcon = painterResource(id = R.drawable.contrast_icon), text = "Theme", onClick = {})

                SettingsSeparator(text = "Edit")
                SettingsBar(painterIcon = painterResource(id = R.drawable.account_icon), text = "Edit account", onClick = { Navigation.goTo(Screens.DISPLAY, nav, componentId = FleetApplication.fleetModule.tenantId)})
                SettingsBar(painterIcon = painterResource(id = R.drawable.door_icon), text = "Edit apartment", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(painterIcon = painterResource(id = R.drawable.building_icon), text = "Edit building", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})

                SettingsSeparator(text = "Account")
                SettingsBar(painterIcon = painterResource(id = R.drawable.key_icon), text = "Change account", onClick = { Navigation.goTo(Screens.SIGN_IN, nav) })//Todo make whole app restart or remake all Jobs
                SettingsBar(painterIcon = painterResource(id = R.drawable.lock_icon), text = "Privacy", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(painterIcon = painterResource(id = R.drawable.list_icon), text = "Terms of service", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})

                SettingsSeparator(text = "Help the dev")
                SettingsBar(painterIcon = painterResource(id = R.drawable.message_icon), text = "Message the dev", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})//Remove ->
                SettingsBar(painterIcon = painterResource(id = R.drawable.poll_icon), text = "Answer a poll", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(painterIcon = painterResource(id = R.drawable.image_icon), text = "Get resources", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(painterIcon = painterResource(id = R.drawable.bug_icon), text = "Report problem", onClick = {Navigation.goTo(Screens.WORK_IN_PROGRESS, nav)})//Remove <-


                if (viewModel.showColorSelector){
                    ColorSelector(
                        onConfirm = {viewModel.changeSettingsColor(it); viewModel.toggleColorPalette()},
                        onDismiss = {viewModel.toggleColorPalette()},
                    )
                }
            }
        }
    }
}


//https://fonts.google.com/icons

@Composable
fun SettingsBar(
    modifier: Modifier = Modifier,
    iconVector: ImageVector? = null,
    painterIcon: Painter? = null,
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
            if (iconVector != null) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = modifier
                        .size(32.dp)
                )
            }
            else{
                Icon(
                    painter = painterIcon!!,
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = modifier
                        .size(32.dp)
                )
            }
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

//
@Composable
fun SettingsSeparator(
    text: String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = text ,
            textAlign = TextAlign.Center,
            color = Color(255,255,255),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
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