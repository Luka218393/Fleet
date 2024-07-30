package com.example.fleet.presentation.components.scaffold_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fleet.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit,
    onTextPress: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { onBack() }, modifier.padding( end = 4.dp)){
                Icon(
                    Icons.Default.ArrowBack, contentDescription = "Back arrow",
                    modifier = modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        actions = {
            Image(
                painter = painterResource(id = R.drawable.lukinaikona),
                contentDescription = "Logo",
                modifier = modifier
                    .size(52.dp)
                    .clickable { /*Todo enlarge image*/ }
            )
        },
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.headlineSmall ,
                modifier = modifier
                    .clickable { onTextPress() }
            )
        },
    )     //
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onClick() },modifier.padding(start = 4.dp, end = 8.dp)) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = "Back arrow",
                    modifier = modifier.size(32.dp)
                )
            }
        },
        title = {
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    style = MaterialTheme.typography.headlineSmall ,
                )
            }
        }
    )
}