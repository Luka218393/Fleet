package com.example.fleet.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


//Todo automatically break lines
@Composable
fun InputField(
    value: MutableState<String>,
    placeholder: String = "",
    maxLines: Int = 1
){
    BasicTextField(
        value = value.value,
        onValueChange = {value.value = it},
        modifier = Modifier
            .fillMaxWidth(1f),
        maxLines = maxLines,
        decorationBox = {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .heightIn(min = 30.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Box(
                    modifier = Modifier
                        .padding(4.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    if(value.value.isEmpty()){
                        Text(placeholder)
                    }
                    it()
                }
            }
        },
    )
}
//Todo play with keyboard options
//InputField for a int value
@Composable
fun InputField_Int(
    value: MutableState<Int>,
    placeholder: String = "",
    maxLines: Int = 1
){
    var displayValue by remember {
        mutableStateOf(value.value.toString())
    }
    BasicTextField(
        value = displayValue,
        onValueChange = {
            displayValue = it
            value.value = it.toInt()
        },
        modifier = Modifier
            .fillMaxWidth(1f),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),//Todo
        decorationBox = {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .heightIn(min = 30.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Box(
                    modifier = Modifier
                        .padding(4.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    if(displayValue.isEmpty()){
                        Text(placeholder)
                    }
                    it()
                }
            }
        },
    )
}


@Composable
fun UnderlinedInputField(
    value: MutableState<String>,
    placeholder: String = "",
    maxLines: Int = 1,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
){
    BasicTextField(
        value = value.value,
        onValueChange = {value.value = it},
        modifier = Modifier.fillMaxWidth(0.9f),
        maxLines = maxLines,
        textStyle = textStyle,
        decorationBox = {
            Column(
                horizontalAlignment = AbsoluteAlignment.Left,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = AbsoluteAlignment.CenterRight
                ){
                    if(value.value.isEmpty()){
                        Text(placeholder)
                    }
                    it()
                }
                HorizontalDivider(
                    thickness = 2.dp,
                )
            }
        },
    )
}



@Composable
fun UnderlinedInputField_Int(
    value: MutableState<Int>,
    placeholder: String = "",
    maxLines: Int = 1
) {
    var displayValue by remember {
        mutableStateOf(value.value.toString())
    }
    BasicTextField(
        value = displayValue,
        onValueChange = { value.value = it.toInt(); displayValue = it },
        modifier = Modifier.fillMaxWidth(0.9f),
        maxLines = maxLines,
        textStyle = MaterialTheme.typography.bodyMedium,
        decorationBox = {
            Column(
                horizontalAlignment = AbsoluteAlignment.Left,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = AbsoluteAlignment.CenterRight
                ){
                    if(value.value.toString().isEmpty()){
                        Text(placeholder)
                    }
                    it()
                }
                HorizontalDivider(
                    thickness = 2.dp,
                )
            }
        },
    )
}

