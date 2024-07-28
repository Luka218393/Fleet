package com.example.fleet.presentation.components.scaffold_elements

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
import com.example.fleet.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit,
    onTextPress: () -> Unit,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = modifier
                    .clickable { onBack() }
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
                    .fillMaxSize()
                    .clickable { onTextPress() },
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
fun SimpleTopBar(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = modifier
                    .clickable { onClick() }
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
                    text = text
                )
            }
        }
    )
}