package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.DialogueViewModel
import com.example.fleet.presentation.fragments.TopBar


//Todo come up with reasonable name for this
class DialogueScreen (
    private val viewModel: DialogueViewModel,
    private val navigation: Navigation,
) : Screen{
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { TopBar(Modifier, navigation) }
        ) { padding ->
            Column(
                modifier = Modifier
                .padding(padding)
                .fillMaxSize()
            ) {
                for (i in viewModel.getMessages(1)){//Todo Make this work
                    i.Create(tenantId = viewModel.getSettingsTenantId())
                }
            }
        }
    }

}

