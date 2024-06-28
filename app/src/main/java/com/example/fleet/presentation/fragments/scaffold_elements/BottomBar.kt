package com.example.fleet.presentation.fragments.scaffold_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation

//Todo move this to object
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
) {
    val nav = LocalNavigator.current
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        actions = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(36.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { Navigation.goTo(Screens.NOTIFICATION, nav )}) {
                    Icon(
                        Icons.Default.Star, contentDescription = "Notifications",
                        modifier = modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = { Navigation.goTo(screen = Screens.CHAT_SELECTION, nav) }) {
                    Icon(
                        Icons.Default.Home, contentDescription = "Chat",
                        modifier = modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = { Navigation.goTo(Screens.SETTINGS, nav) }) {
                    Icon(
                        Icons.Default.Settings, contentDescription = "Settings",
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}
//Todo change text style
//Todo Make this functional and preatty
@Composable
fun InputBottomBar(
    modifier: Modifier,
    send: (String) -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
    ){
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){


        var text by remember{mutableStateOf("")}
        BasicTextField(
            value = text,
            onValueChange = {text = it},
            modifier = modifier
                //.width(250.dp)
                .weight(3f)
                ,
            decorationBox = {
                Row(
                    modifier = Modifier
                        .wrapContentSize().padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.tertiary)
                        .width(300.dp)
                        .heightIn(min = 30.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                    ){
                        it()
                    }
                }
            },
        )

        IconButton(
            onClick = {
                send(text)
                text = ""
            },
            modifier = modifier
                .weight(1f)
                .fillMaxHeight(),
            enabled = text.isNotEmpty()
        ){
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}
}


@Preview
@Composable
fun InputBottomBarPreview(){
    InputBottomBar(modifier = Modifier, send = {})
}