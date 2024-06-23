package com.example.fleet.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory

//Todo Come up with a normal name for this class
class ChatScreen (
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java],

) : BaseScreen(){
    @Composable
    override fun InnerContent() {
        val nav = LocalNavigator.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewModel.chatBars.size) { index ->
                viewModel.chatBars[index].Create(navigateToDialogueScreen = { Navigation.goTo( Screens.DIALOGUE_SCREEN, nav, index + 1) } )
            }
        }
    }
}
