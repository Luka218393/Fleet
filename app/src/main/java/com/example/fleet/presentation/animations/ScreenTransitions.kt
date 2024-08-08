package com.example.fleet.presentation.animations

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import com.example.fleet.domain.Enums.Screens
private val tag = "Screen transition"

@Composable
fun CustomScreenTransition(
    navigator: Navigator,
    content: ScreenTransitionContent
) {
    var lastScreenIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    var temp by rememberSaveable { mutableStateOf<Int?>(null) }
    val animationDuration = 500


    ScreenTransition(
        navigator = navigator,
        content = content,
        transition = {
            val transitionLeft = slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(animationDuration)
            ) togetherWith  slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(animationDuration)
            )

            val transitionRight = slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(animationDuration)
            ) togetherWith  slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(animationDuration)
            )

            val transitionScale = scaleIn(
                animationSpec = tween(animationDuration),
            ) togetherWith scaleOut(
                animationSpec = tween(1, delayMillis = animationDuration)
            )

            var currentScreenIndex: Int? = null
            when (navigator.lastItem.key) {
                Screens.NOTIFICATION.key -> currentScreenIndex = 1
                Screens.CHAT_SELECTION.key -> currentScreenIndex = 2
                Screens.SETTINGS.key -> currentScreenIndex = 3
            }
            Log.i(tag, currentScreenIndex.toString())
            Log.i(tag, lastScreenIndex.toString())
            temp = currentScreenIndex
            if (currentScreenIndex == null || lastScreenIndex == null) transitionScale
            else if (currentScreenIndex > lastScreenIndex!!) transitionLeft
            else if (currentScreenIndex < lastScreenIndex!!) transitionRight
            else transitionScale


        }//.then { lastScreenIndex = currentScreenIndex }
    )

    lastScreenIndex = temp
}

@Composable
fun slideAnimation(){

}