package com.example.fleet.useless_code

/*
//Todo Make this smarter
//Hides home and back buttons
WindowCompat.setDecorFitsSystemWindows(window, false)
val controller = WindowCompat.getInsetsController(window, window.decorView)
controller.hide(WindowInsetsCompat.Type.systemBars())
controller.addOnControllableInsetsChangedListener { _, typeMask ->
    var systemBarsVisible = typeMask and WindowInsetsCompat.Type.systemBars() != 0
}











TODO implement this to viewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class MyViewModel(
    private val myRepository: MyRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // ViewModel logic

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).myRepository
                MyViewModel(
                    myRepository = myRepository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}





//Todo what should chatscreen be Activity or fragment
@Composable
fun ChatScreen(
    modifier : Modifier = Modifier,
    chat: Chat,
    messages: List<Message>
){
    for (message in messages){
        Text(text = message.text)
    }
}













































*/