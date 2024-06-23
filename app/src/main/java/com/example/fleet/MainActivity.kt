package com.example.fleet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.Tenants
import com.example.fleet.data.apartments
import com.example.fleet.data.buildings
import com.example.fleet.data.chats
import com.example.fleet.data.messages
import com.example.fleet.data.notifications
import com.example.fleet.data.pollOptions
import com.example.fleet.data.polls
import com.example.fleet.data.settings1
import com.example.fleet.data.tasks
import com.example.fleet.data.tenantChat
import com.example.fleet.presentation.screens.ChatScreen
import com.example.fleet.presentation.ui.theme.FleetTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
This is the starting point of the app.
*/
//Todo what is a service???
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme {

                //seed(db)

                //val mainViewModel = ViewModelProvider(FleetApplication.fleetModule.viewModelStore, MainViewModelFactory())[MainViewModel::class.java]

                Navigator(ChatScreen())
            }
        }
    }
}

fun seed(db: FleetDatabase){
    runBlocking{
        for (i in buildings) {
            db.buildingDao().upsert(i)
        }
        for ( i in apartments){
            db.apartmentDao().upsert(i)
        }
        db.tenantDao().upsert(Tenants().tenant1)
        db.tenantDao().upsert(Tenants().tenant2)
        db.tenantDao().upsert(Tenants().tenant3)
        for (i in notifications) {
            db.notificationDao().upsert(i)
        }
        db.settingsDao().upsert(settings1)
        val settings = MutableStateFlow(db.settingsDao().get().first())

        for (i in polls){
            db.pollDao().upsert(i)
        }
        for (i in pollOptions) {
            db.pollOptionDao().upsert(i)
        }
        for (i in tasks){
            db.taskDao().upsert(i)
        }
        for (i in chats){
            db.chatDao().upsert(i)
        }
        for (i in tenantChat){
            db.tenantChatDao().upsert(i)
        }
        for (i  in messages){
            db.messageDao().upsert(i)
        }
        Log.i("aaa", settings.toString())
    }
}
