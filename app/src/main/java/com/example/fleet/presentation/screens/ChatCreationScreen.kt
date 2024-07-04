package com.example.fleet.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import com.example.fleet.FleetApplication
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.fragments.Create_ChatBar
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.NewChatTopBar

class ChatCreationScreen: Screen{
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]
    //Todo filter chats you already have private chat with
    //Todo add filter
    //Todo Create Chat
    //Todo remove yourself
    @Composable
    override fun Content() {
        val modifier = Modifier
        var id by remember { mutableIntStateOf(1) }

        var isChatPrivate by remember { mutableStateOf(false) }

        val tenantsToDisplay = viewModel.tenants.collectAsState(emptyList()).value//Todo change ChatBars
        val selectedChats = rememberSaveable <MutableList<Int>> { mutableListOf() }


        Log.i("ChatCreationScreen",selectedChats.toString())
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = { NewChatTopBar() },
            bottomBar = { BottomBar() },
            ) { padding ->
                Column (
                    modifier = modifier.padding(padding)
                ){
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = { isChatPrivate = true; selectedChats.clear()  },
                            colors = if (isChatPrivate) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.onSecondary) else ButtonDefaults.buttonColors()
                        ) {
                            Text(text = "Private")
                        }
                        Spacer(modifier.width(20.dp))
                        Button(
                            onClick = { isChatPrivate = false; selectedChats.clear() },
                            colors = if (!isChatPrivate) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.onSecondary) else ButtonDefaults.buttonColors()

                        ) {
                            Text(text = "Group")
                        }
                    }
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(tenantsToDisplay) { tenant ->
                            Create_ChatBar(
                                tenant = tenant,
                                id = remember{id},
                                onClick = {
                                    if(isChatPrivate) selectedChats.clear()
                                    selectedChats.add(it)
                                    Log.i("ChatCreationScreen",selectedChats.toString())
                                },
                                onDismiss = {
                                    selectedChats.remove(it);

                                    Log.i("ChatCreationScreen",selectedChats.toString())
                                },
                                isBarSelected = {it in selectedChats}

                            )
                            id++
                        }
                    }
                }
        }
    }
}