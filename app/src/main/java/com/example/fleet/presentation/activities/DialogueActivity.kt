package com.example.fleet.presentation.activities

import androidx.compose.runtime.Composable
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.DialogueViewModel

class DialogueActivity (
    private val dialogueViewModel: DialogueViewModel,
    navigation: Navigation
) : BaseActivity(navigation){
    @Composable
    override fun InnerContent() {
        /*Todo Add Screen that represents a chat*/
    }

}