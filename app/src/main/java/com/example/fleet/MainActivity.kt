package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.presentation.screens.DisplayScreen
import com.example.fleet.presentation.ui.theme.FleetTheme

/**
This is the starting point of the app.
*/
//Todo what is a service???
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme (
                content = {
                    Navigator(DisplayScreen(tenantId = 7))
                    //Todo add this to Other inputs
                    //val a = remember{ mutableStateOf(1)}
                    /*CustomListSelector(
                        width = 200.dp,
                        itemHeight = 30.dp,
                        items = listOf("1,2,3,4,5,6", "sdlfdshlb", "July"),
                        initialItem = "July",
                        textStyle = MaterialTheme.typography.bodyMedium,
                        textColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.secondary,
                        onItemSelected = {_ , b -> a.value = b}
                    )*/
                    /*Column{
                        UnderlinedInputField(value = a, keyboardType = KeyboardType.Number) {
                            a.value = it.toInt()
                        }
                        Text(a.value.toString())
                    }*/
                }
            )
        }
    }
}
//https://stackoverflow.com/questions/69734451/is-there-a-way-to-create-scroll-wheel-in-jetpack-compose



