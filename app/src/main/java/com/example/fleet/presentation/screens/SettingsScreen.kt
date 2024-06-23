package com.example.fleet.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fleet.R
import com.example.fleet.domain.Models.SettingsState
import com.example.fleet.domain.Navigation

class SettingsScreen (
    private val settings: SettingsState,
    navigation: Navigation
) : BaseScreen(navigation){
    @Composable
    override fun InnerContent() {
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(){
                Image(painter = painterResource(id = R.drawable.lukinaikona), contentDescription = "Pokemons" )
                Column(){
                    Text(text = settings.tenant.name)
                    Text(text = settings.tenant.name)
                }
            }
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .height(10.dp)
                    .fillMaxWidth()
            ){
                Text(text = "TagsBar")
            }

            /*TODO add text and input field to every setting*/
        }
    }
}