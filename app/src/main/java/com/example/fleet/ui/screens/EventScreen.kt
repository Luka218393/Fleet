package com.example.fleet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.R
import com.example.fleet.data.Models.PollOption
import com.example.fleet.ui.elements.BaseCard
import com.example.fleet.ui.elements.BottomBar
import com.example.fleet.ui.elements.ImageEventCard
import com.example.fleet.ui.elements.PollCard
import com.example.fleet.ui.elements.SimpleEventCard



class EventScreen(
    private val bottomBar: @Composable () -> Unit = {},
    private val cards: List<BaseCard> = listOf(
        SimpleEventCard(iconResId = Icons.Default.AccountBox, title = "Novi stanar",text = "Pozdravite novog stanara Rodiona Romanoviča Raskoljinkova..."),
        PollCard(question = "Hoćemo li počastiti novog stanara?", options = listOf(PollOption("Da", 1), PollOption("Ne", 0),PollOption("Možda", 0),PollOption("Nikada", 0),PollOption("Vidi pokemon", 0))),
        ImageEventCard(iconResId = Icons.Default.Build, text = "Danas je pukla cijev odvodnje na 3. katu. Molim stanare da budu strpljivi, te da imaju razumijevanja. Stvari će ubrzo biti popravljene",title = "Pukla je cijev", imageResId = R.drawable.lukinaikona))
)
    :BaseScreen()
{
    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cards.size) { index ->
                cards[index].Create()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    EventScreen(
        bottomBar = {BottomBar(modifier = Modifier)}
    ).Create()
}