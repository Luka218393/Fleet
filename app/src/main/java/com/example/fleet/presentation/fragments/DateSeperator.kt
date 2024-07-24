package com.example.fleet.presentation.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fleet.presentation.HelperFunctions
import java.time.LocalDateTime


@Composable
fun DateSeparator(
    date: LocalDateTime
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        HorizontalDivider(modifier = Modifier.weight(2f))
        Text(
            text = HelperFunctions.getDayAndMonth(date) ,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color(255,255,255)
        )
        HorizontalDivider(modifier = Modifier.weight(2f))
    }
}

@Preview
@Composable
fun DateSeparatorPreview(){
    //DateSeparator(date = Date())
}