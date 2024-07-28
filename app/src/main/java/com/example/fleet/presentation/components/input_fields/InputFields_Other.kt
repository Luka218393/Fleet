package com.example.fleet.presentation.components.input_fields

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fleet.presentation.HelperFunctions
import java.time.LocalDate
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> CustomListSelector(
    width: Dp,
    itemHeight: Dp,
    numberOfDisplayedItems: Int = 3,
    items: List<T>,
    initialItem: T,
    itemScaleFact: Float = 1.5f,
    textStyle: TextStyle,
    textColor: Color,
    selectedTextColor: Color,
    onItemSelected: (index: Int, item: T) -> Unit
) {
    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx() / 2f }
    val scrollState = rememberLazyListState(0)
    var lastSelectedIndex by remember {
        mutableStateOf(0)
    }
    var itemsState by remember {
        mutableStateOf(items)
    }
    LaunchedEffect(items) {
        var targetIndex = items.indexOf(initialItem)- 1
        targetIndex += ((Int.MAX_VALUE / 2) / items.size) * items.size
        itemsState = items
        lastSelectedIndex = targetIndex
        scrollState.scrollToItem(targetIndex)
    }
    LazyColumn(
        modifier = Modifier
            .width(width)
            .height(itemHeight * numberOfDisplayedItems),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(
            lazyListState = scrollState
        )
    ) {
        items(
            count = Int.MAX_VALUE,
            itemContent = { i ->
                val item = itemsState[i % itemsState.size]
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            val y = coordinates.positionInParent().y - itemHalfHeight
                            val parentHalfHeight =
                                (coordinates.parentCoordinates?.size?.height ?: 0) / 2f
                            val isSelected =
                                (y > parentHalfHeight - itemHalfHeight && y < parentHalfHeight + itemHalfHeight)
                            if (isSelected && lastSelectedIndex != i) {
                                onItemSelected(i % itemsState.size, item)
                                lastSelectedIndex = i
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.toString(),
                        style = textStyle,
                        color = if (lastSelectedIndex == i) {
                            selectedTextColor
                        } else {
                            textColor
                        },
                        fontSize = if (lastSelectedIndex == i) {
                            textStyle.fontSize * itemScaleFact
                        } else {
                            textStyle.fontSize
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun DateSelector(
    date: LocalDate,
    displayButton: Boolean = true,
    onChange:(LocalDate)->Unit,
) {
    var day by remember{mutableStateOf(date.dayOfMonth.toString())}
    var month by remember{ mutableStateOf(date.monthValue.toString())}
    var year by remember { mutableStateOf(date.year.toString()) }

    Row{
        CustomListSelector(
            width = 40.dp,
            itemHeight = 30.dp,
            items = List(LocalDate.now().lengthOfMonth()) { (1+it).toString() },
            initialItem = day,
            textStyle = MaterialTheme.typography.bodyMedium,
            textColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.secondary,
            numberOfDisplayedItems = 3
        ){_,item -> day = item}
        CustomListSelector(
            width = 100.dp,
            itemHeight = 30.dp,
            items = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
            initialItem = date.month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault()),
            textStyle = MaterialTheme.typography.bodyMedium,
            textColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.secondary,
            numberOfDisplayedItems = 3
        ){item,_ -> month = (item + 1).toString()}
        CustomListSelector(
            width = 50.dp,
            itemHeight = 30.dp,
            items = List(101) { (it + LocalDate.now().year - 100).toString() },
            initialItem = year,
            textStyle = MaterialTheme.typography.bodyMedium,
            textColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.secondary,
            numberOfDisplayedItems = 3
        ){_,item -> year = item}
    }
    if (displayButton){
        Button(onClick = {
            onChange(HelperFunctions.stringToLocalDate(day,month,year))
        }){
            Text("Change")
        }
    }
    else{
        LaunchedEffect(day,month,year){
            onChange(HelperFunctions.stringToLocalDate(day,month,year))
        }
    }

}

@Composable
@Preview
fun PreviewInfiniteCircularList(){
    CustomListSelector(
        width = 200.dp,
        itemHeight = 30.dp,
        items = listOf(1,2,3,4,5,6),
        initialItem = 2,
        textStyle = MaterialTheme.typography.bodyMedium,
        textColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.secondary
    ){_,_ ->}
}