package com.example.fleet.presentation.screens.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.domain.navigation.MainNavigation
import com.example.fleet.domain.navigation.Screens
import com.example.fleet.domain.viewModels.SettingsViewModel
import com.example.fleet.domain.viewModels.SettingsViewModelFactory
import com.example.fleet.presentation.components.ColorSelector


//Todo add scroll
//Todo give this normal icons
class SettingsScreen : Screen{

    @Transient
    private val viewModel: SettingsViewModel = ViewModelProvider(FleetApplication.viewModelStore, SettingsViewModelFactory())[SettingsViewModel::class.java]

    private val TAG = "SettingsScreen"

    override val key: ScreenKey
        get() = Screens.SETTINGS.key

    @Stable
    @Composable
    override fun Content() {
        var i = 0
        //Todo try this
        viewModel.apply{}
        val nav = LocalNavigator.current
        Scaffold{ padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ){
                i++

                SettingsSeparator(text = "Theme")
                SettingsBar(icon = Icons.Default.Create, text = "Color", onClick = {viewModel.toggleColorPalette()})
                SettingsBar(icon = painterResource(id = R.drawable.four_star_icon), text = "Immersive mode", onClick = {viewModel.toggleImmersiveMode()})
                SettingsBar(icon = painterResource(id = R.drawable.contrast_icon), text = "Theme", onClick = { MainNavigation.goTo(
                    Screens.WORK_IN_PROGRESS, nav)})

                SettingsSeparator(text = "Edit")
                SettingsBar(icon = painterResource(id = R.drawable.account_icon), text = "Edit account", onClick = { MainNavigation.goTo(
                    Screens.DISPLAY_TENANT, nav, componentId = FleetApplication.fleetModule.tenantId)})
                SettingsBar(icon = painterResource(id = R.drawable.door_icon), text = "Edit apartment", onClick = { MainNavigation.goTo(
                    Screens.DISPLAY_APARTMENT, nav, componentId = FleetApplication.fleetModule.apartment.value.id)})
                SettingsBar(icon = painterResource(id = R.drawable.building_icon), text = "Edit building", onClick = { MainNavigation.goTo(
                    Screens.DISPLAY_BUILDING, nav, componentId = FleetApplication.fleetModule.building.value.id)})

                SettingsSeparator(text = "Account")
                SettingsBar(icon = painterResource(id = R.drawable.fleet_icon), text = "Change account", onClick = { MainNavigation.goTo(
                    Screens.LOG_IN, nav) })//Todo make whole app restart or remake all Jobs
                SettingsBar(icon = painterResource(id = R.drawable.lock_icon), text = "Privacy", onClick = { MainNavigation.goTo(
                    Screens.WORK_IN_PROGRESS, nav )})
                SettingsBar(icon = painterResource(id = R.drawable.list_icon), text = "Terms of service", onClick = {            Log.i(TAG, i.toString())
                })

                SettingsSeparator(text = "Help the dev")
                SettingsBar(icon = painterResource(id = R.drawable.message_icon), text = "Message the dev", onClick = { MainNavigation.goTo(
                    Screens.WORK_IN_PROGRESS, nav)})//Remove ->
                SettingsBar(icon = painterResource(id = R.drawable.poll_icon), text = "Answer a poll", onClick = { MainNavigation.goTo(
                    Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(icon = painterResource(id = R.drawable.image_icon), text = "Get resources", onClick = { MainNavigation.goTo(
                    Screens.WORK_IN_PROGRESS, nav)})
                SettingsBar(icon = painterResource(id = R.drawable.bug_icon), text = "Report problem", onClick = { MainNavigation.goTo(
                    Screens.WORK_IN_PROGRESS, nav)})//Remove <-


                if (viewModel.showColorSelector){
                    ColorSelector(
                        onConfirm = {viewModel.changeSettingsColor(it); viewModel.toggleColorPalette()},
                        onDismiss = {viewModel.toggleColorPalette()},
                    )
                }
            }
        }//https://fonts.google.com/icons
    }
}



@Stable
@Composable
fun SettingsBar(
    modifier: Modifier = Modifier,
    icon: Any? = null,
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
            when (icon) {
                is ImageVector -> Icon(
                    imageVector = icon,
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                is Painter -> Icon(
                    painter = icon,
                    contentDescription = text,
                    tint = MaterialTheme.colorScheme.primary,modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style  = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "More",
            tint = MaterialTheme.colorScheme.primary,
            modifier = modifier
                .size(32.dp)
        )
    }
}

//
@Stable
@Composable
fun SettingsSeparator(
    text: String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.outlineVariant)
        Text(
            text = text ,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.outlineVariant)
    }
}


@Preview
@Composable
fun SettingsBarPreview(){
    SettingsBar(
        icon = Icons.Default.Check,
        text = "More settings",
        onClick = {}
    )
}