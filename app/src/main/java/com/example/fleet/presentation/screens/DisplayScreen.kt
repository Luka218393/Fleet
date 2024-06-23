package com.example.fleet.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.fleet.R
import com.example.fleet.data.Tenants
import com.example.fleet.domain.Models.Tenant

class DisplayScreen: BaseScreen(){
    @Composable
    override fun InnerContent() {
        displayTenant()
    }

    @Composable
    private fun displayTenant(
        tenant: Tenant = Tenants().tenant1
    ){
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Row(){
                Image(painter = painterResource(id = tenant.profileImageRes ?: R.drawable.lukinaikona), contentDescription = tenant.name )
                Column(){

                }
            }
            /*TODO add text and input field to every setting*/
        }
    }
}