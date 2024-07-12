package com.example.fleet.presentation.settings_fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.fleet.FleetApplication
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorSelector(
    onDismiss: () -> Unit,
    onConfirm: (Color) -> Unit
){
    val controller = rememberColorPickerController()
    Dialog(
        onDismissRequest = {onDismiss()},
        //Todo select last position
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(10.dp),
                    controller = controller,
                    initialColor = Color(FleetApplication.fleetModule.settings.value.appColor.toULong())
                )

                BrightnessSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(48.dp),
                    controller = controller,
                )

                AlphaTile(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(10.dp),

                    controller = controller
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    IconButton(onClick = { onDismiss() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Create notification dialog")
                    }
                    IconButton(onClick = { onConfirm(controller.selectedColor.value) }) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Create notification dialog")
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ColorSelectorPreview(){
    ColorSelector ({},{})
}