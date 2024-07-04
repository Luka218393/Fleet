package com.example.fleet.presentation.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory

class ChatCreationScreen: Screen{
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]
    @Composable
    override fun Content() {

        /*val chatBars = viewModel.chatBars.collectAsState(emptyList()).value//Todo change ChatBars

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { NewChatTopBar() },
            bottomBar = { BottomBar() },
            ) { padding ->
                Column (
                    modifier = Modifier.padding(padding)
                ){
                    Row{
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "")
                        }
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "")
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(chatBars) { chatBar ->
                            chatBar.Create(navigateToDialogueScreen = {
                                Navigation.goTo( Screens.CHAT, chatBar.chat.id)
                                viewModel.changeMessageCollectorJob(chatBar.chat.id)
                            } )
                        }
                    }
                }
        }*/
    }
}