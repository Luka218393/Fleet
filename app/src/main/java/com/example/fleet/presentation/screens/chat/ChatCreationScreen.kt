package com.example.fleet.presentation.screens.chat

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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.fleet.presentation.components.SimplifiedChatBar
import com.example.fleet.presentation.components.input_fields.InputField
import com.example.fleet.presentation.components.scaffold_elements.SimpleFloatingButton
import com.example.fleet.presentation.components.scaffold_elements.SimpleTopBar

//
class ChatCreationScreen: Screen {
    @Transient
    private val viewModel: ChatViewModel = ViewModelProvider(FleetApplication.viewModelStore, ChatViewModelFactory())[ChatViewModel::class.java]

    //Todo rename all chat types to is personal / group
    //Todo add filter
    //Todo make floating button change color if no chats are selected
    //Todo Remove floating button?
    //Todo Add everyone to chat button
    @Composable
    override fun Content() {
        val modifier = Modifier
        val nav = LocalNavigator.current
        val tenantsToDisplay = viewModel.tenants.collectAsState(emptyList()).value
        var isPersonal by remember { mutableStateOf(false) }
        val selectedTenants = remember { mutableStateListOf<String>() }
        var displayChatDialog by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = modifier) {
            viewModel.insertTenantsForChatCreation(isPersonal)
        }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = { SimpleTopBar(text = "New chat", onClick = { Navigation.pop(nav) }) },
            floatingActionButton = { SimpleFloatingButton(onclick = {if(selectedTenants.isNotEmpty()) displayChatDialog = !displayChatDialog }, icon = Icons.Default.Add) },
            bottomBar = { },
        ) { padding ->
            Column(
                modifier = modifier.padding(padding)
            ) {

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ChatPrivacyButtons(isPersonal = isPersonal, clearSelectedTenants = { selectedTenants.clear() }, toggleChatPrivacy = {isPersonal = it}){
                        viewModel.insertTenantsForChatCreation(it)
                    }
                }

                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(tenantsToDisplay, key = { it.id }) { tenant ->
                        SimplifiedChatBar(
                            tenant = tenant,
                            onClick = {
                                if (isPersonal) selectedTenants.clear()
                                selectedTenants.add(it)
                            },
                            onDismiss = { selectedTenants.remove(it) },
                            isBarSelected = { it in selectedTenants }
                        )
                    }
                }
            }

            if (displayChatDialog && !isPersonal) {
                ChatDialog({
                           displayChatDialog = !displayChatDialog
                }, { title ->
                    viewModel.createChat(selectedTenants, isPersonal, title)
                    selectedTenants.clear()
                    Navigation.pop(nav)
                })
            }
            else if(displayChatDialog){
                viewModel.createChat(selectedTenants, true)
                selectedTenants.clear()
                Navigation.pop(nav)
            }
        }
    }
}

//
@Composable
fun ChatPrivacyButtons(
    isPersonal: Boolean,
    clearSelectedTenants: ()->Unit,
    modifier: Modifier = Modifier,
    toggleChatPrivacy: (Boolean)->Unit,
    updateTenantsToDisplay: (Boolean)->Unit
){
    Button(
        onClick = { toggleChatPrivacy(false); clearSelectedTenants(); updateTenantsToDisplay(false) },
        colors = if (!isPersonal) ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) else ButtonDefaults.buttonColors()

    ) {
        Text(text = "Group")
    }
    Spacer(modifier.width(20.dp))
    Button(
        onClick = { toggleChatPrivacy(true); clearSelectedTenants();updateTenantsToDisplay(true) },
        colors = if (isPersonal) ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) else ButtonDefaults.buttonColors()
    ) {
        Text(text = "Personal")
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
                    Column(modifier = Modifier.padding(4.dp)){
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

