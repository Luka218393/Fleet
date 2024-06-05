package com.example.fleet.presentation.activities

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.domain.Enums.Screens
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.MainViewModel
import com.example.fleet.domain.viewModels.MainViewModelFactory
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.fragments.BaseCard
import com.example.fleet.presentation.fragments.BottomBar


class NotificationActivity(
    private val modifier: Modifier = Modifier,
    private val cards: List<BaseCard> = com.example.fleet.data.cards,
    private val viewModel: NotificationViewModel,
    navigation: Navigation

)
    : BaseActivity(navigation)
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
