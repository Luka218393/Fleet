package com.example.fleet.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.FleetApplication
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.ChatViewModel
import com.example.fleet.domain.viewModels.ChatViewModelFactory
import com.example.fleet.presentation.fragments.Create_ChatBar
import com.example.fleet.presentation.fragments.input_fields.InputField
import com.example.fleet.presentation.fragments.scaffold_elements.BottomBar
import com.example.fleet.presentation.fragments.scaffold_elements.NewChatTopBar
import com.example.fleet.presentation.fragments.scaffold_elements.SimpleFloatingButton

class ChatCreationScreen: Screen {
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]

    //Todo filter chats you already have private chat with
    //Todo add filter
    //Todo Create Chat
    //Todo remove yourself
    //Todo make floating button unclickable if no chats are selected
    //Todo Remove floating button?
    @Composable
    override fun Content() {
        val modifier = Modifier
        val nav = LocalNavigator.current
        val tenantsToDisplay = viewModel.tenants.collectAsState(emptyList()).value
        var isPrivate by remember { mutableStateOf(false) }
        val selectedTenants = remember { mutableStateListOf<Int>() }
        var displayChatDialog by remember { mutableStateOf(false) }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = { NewChatTopBar() },
            floatingActionButton = {
                SimpleFloatingButton(onclick = { displayChatDialog = !displayChatDialog }, icon = Icons.Default.Add)
            },
            bottomBar = { BottomBar() },
        ) { padding ->
            Column(
                modifier = modifier.padding(padding)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { isPrivate = true; selectedTenants.clear() },
                        colors = if (isPrivate) ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ) else ButtonDefaults.buttonColors()
                    ) {
                        Text(text = "Private")
                    }
                    Spacer(modifier.width(20.dp))
                    Button(
                        onClick = { isPrivate = false; selectedTenants.clear() },
                        colors = if (!isPrivate) ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ) else ButtonDefaults.buttonColors()

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
                    items(tenantsToDisplay, key = { it.id }) { tenant ->
                        Create_ChatBar(
                            tenant = tenant,
                            onClick = {
                                if (isPrivate) selectedTenants.clear()
                                selectedTenants.add(it)
                                Log.i("ChatCreationScreen", selectedTenants.toString())
                            },
                            onDismiss = {
                                selectedTenants.remove(it);

                                Log.i("ChatCreationScreen", selectedTenants.toString())
                            },
                            isBarSelected = { it in selectedTenants }

                        )
                    }
                }
            }
            //Todo remove this dialog from private chats and give them persons name
            if (displayChatDialog) {
                ChatDialog({
                           displayChatDialog = !displayChatDialog
                }, { title ->
                    viewModel.createChat(selectedTenants, isPrivate, title)
                    selectedTenants.clear()
                    Navigation.pop(nav)
                })
            }
        }
    }
}

//Todo add ability to change profile image
@Composable
fun ChatDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val title = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Column (
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = "Chat title",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Card {
                    Column{

                        InputField(title, "Title"){title.value = it}

                    }
                }
                IconButton(
                    onClick = {onConfirm(title.value)},
                    modifier = modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = if (title.value.isNotEmpty()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary),
                    enabled = title.value.isNotEmpty()
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Create Notification")
                }
            }

        }
    )
}

