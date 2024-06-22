package com.example.fleet.presentation.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.NotificationViewModel


class NotificationScreen(
    private val modifier: Modifier = Modifier,
    private val viewModel: NotificationViewModel,
    navigation: Navigation

)
    : BaseScreen(navigation)
{
    @Composable
    override fun InnerContent() {

        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            items(viewModel.cards.size) { index ->
                viewModel.cards[index].Create()
            }
        }
    }
}
