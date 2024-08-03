package com.example.fleet.presentation.components.input_fields

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
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// UI
@Composable
fun <T> InputField(
    value: MutableState<T>,
    placeholder: String = "",
    maxLines: Int = 1,
    onValueChange: (String) -> Unit
){
    val displayValue by remember {
        derivedStateOf { value.value.toString() }
    }
    BasicTextField(
        value = displayValue,
        onValueChange = {onValueChange(it)},
        modifier = Modifier.fillMaxWidth(1f),
        maxLines = maxLines,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
        decorationBox = {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
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
                        Text(
                            placeholder,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                    it()
                }
            }
        },
    )
}

@Composable
fun <T> UnderlinedInputField(
    value: State<T?>,
    placeholder: String = "",
    maxLines: Int = 1,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    val displayValue by remember { derivedStateOf{value.value?.toString() ?: ""} }

    BasicTextField(
        value = displayValue,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(0.9f),
        maxLines = maxLines,
        textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),//Todo
        enabled = enabled,
        decorationBox = {
            Column(
                horizontalAlignment = AbsoluteAlignment.Left,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = AbsoluteAlignment.CenterLeft
                ){
                    if(value.value.toString().isEmpty()){
                        Text(
                            placeholder,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                    it()
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        },
    )
}


@Composable
fun <T> EditableTextField(
    editMode: Boolean,
    value: State<T>,
    placeholder: String,
    maxLines: Int = 1,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
){
    if (editMode) {
        UnderlinedInputField(
            value,
            placeholder,
            maxLines,
            textStyle,
            keyboardType,
            true,
            onValueChange
        )
    } else {
        Text(
            text = value.value.toString(),
            style = textStyle,
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth(0.9f)
        )
    }
}

//Todo fix placeholders
@Composable
fun <T> AttributeDisplay(
    attribute: String,
    value: MutableState<T>,
    editMode: Boolean,
    maxLines: Int = 1,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(top = 28.dp)
    ){
        Text(
            text = attribute,
            style = MaterialTheme.typography.titleMedium,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ){
            EditableTextField(
                editMode,
                value,
                attribute,
                maxLines,
                textStyle,
                keyboardType,
                onValueChange)
        }
    }
}