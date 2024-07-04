package com.example.fleet.presentation.fragments.scaffold_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.R
import com.example.fleet.domain.Navigation


//Todo create 
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    modifier: Modifier,
    title: String,
    nav : Navigator = LocalNavigator.current!!,//Todo this may cause issues ?
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = modifier
                    .clickable { Navigation.pop(nav) }
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,

            ){
                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    Icons.Default.ArrowBack, contentDescription = "Back arrow",
                    modifier = modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))

            }
        },
        title = {
            Row(
                modifier = modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lukinaikona),
                    contentDescription = "Logo",
                    modifier = modifier.size(52.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = title
                )
            }
        }
    )     //
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewChatTopBar(
    modifier: Modifier = Modifier,
    nav : Navigator = LocalNavigator.current!!
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = modifier
                    .clickable { Navigation.pop(nav) }
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,

                ){
                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    Icons.Default.ArrowBack, contentDescription = "Back arrow",
                    modifier = modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))

            }
        },
        title = {
            Row(
                modifier = modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = modifier.width(12.dp))
                Text(
                    text = "New chat"
                )
            }
        }
    )     //
}