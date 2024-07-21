package com.example.fleet.presentation.fragments.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fleet.FleetApplication
import com.example.fleet.presentation.HelperFunctions
import java.util.Date


/**
Base class for event cards and poll cards;
Other cards must inherit from it
*/
abstract class BaseCard(//TODo fix empty card
    val createdAt: Date,
    private val createdBy: Int,
    val id: String
){

    //Content of the card
    @Composable
    abstract fun Content()

    //Creates the card composable
    @Composable
    fun Create(modifier: Modifier = Modifier){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 8.dp),
            shape = RoundedCornerShape(2.dp)
            ) {

            Content()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = FleetApplication.fleetModule.getTenantNameAndSurname(createdBy)?: "Unknown author",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                Text(
                    text = HelperFunctions.getMinAndHour(createdAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        thickness = 0.4.dp,
        color = MaterialTheme.colorScheme.secondary
    )
}

/*
    Card that can display notification and all their additional content
*/




