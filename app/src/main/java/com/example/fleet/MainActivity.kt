package com.example.fleet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.Tenants
import com.example.fleet.data.apartments
import com.example.fleet.data.buildings
import com.example.fleet.data.notifications
import com.example.fleet.data.pollOptions
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.ui.theme.FleetTheme
import kotlinx.coroutines.runBlocking

/**
This is the starting point of the app.
*/
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme {
                val db = FleetDatabase.getDatabase(context = this)
                runBlocking {
                    for (i in buildings) {
                        db.buildingDao().upsert(i)
                    }
                    for ( i in apartments){
                        db.apartmentDao().upsert(i)
                    }/*
                    for (i in pollOptions) {
                        db.pollOptionDao().upsert(i)
                    }*/
                    db.tenantDao().upsert(Tenants().tenant1)
                    db.tenantDao().upsert(Tenants().tenant2)
                    db.tenantDao().upsert(Tenants().tenant3)
                    for (i in notifications) {
                        db.notificationDao().upsert(i)
                    }
                }

                Navigator(NotificationActivity())
            }
        }
    }
}