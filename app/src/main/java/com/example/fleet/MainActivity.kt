package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.apartments
import com.example.fleet.data.buildings
import com.example.fleet.data.chats
import com.example.fleet.data.messages
import com.example.fleet.data.notifications
import com.example.fleet.data.pollOptions
import com.example.fleet.data.polls
import com.example.fleet.data.settings1
import com.example.fleet.data.subTasks
import com.example.fleet.data.tasks
import com.example.fleet.data.tenantChat
import com.example.fleet.data.tenants
import com.example.fleet.presentation.screens.DisplayScreen
import com.example.fleet.presentation.ui.theme.DarkScheme
import com.example.fleet.presentation.ui.theme.FleetTheme
import com.example.fleet.presentation.ui.theme.LightScheme
import kotlinx.coroutines.runBlocking

/**
This is the starting point of the app.
*/
//Todo what is a service???
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme (
                darkScheme = DarkScheme,
                lightScheme = LightScheme,
                content = {
                    Navigator(DisplayScreen())
                }
            )
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
        for ( i in tenants){
            db.tenantDao().upsert(i)
        }
        db.settingsDao().upsert(settings1)
        for (i in notifications) {
            db.notificationDao().upsert(i)
        }
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
        for (i in subTasks){
            db.subTaskDao().upsert(i)
        }
    }
}
