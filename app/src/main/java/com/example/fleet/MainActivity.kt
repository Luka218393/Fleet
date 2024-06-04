package com.example.fleet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.data.FleetDatabase
import com.example.fleet.data.Tenants
import com.example.fleet.data.apartments
import com.example.fleet.data.buildings
import com.example.fleet.data.notifications
import com.example.fleet.data.pollOptions
import com.example.fleet.data.settings1
import com.example.fleet.domain.viewModels.MainViewModel
import com.example.fleet.domain.viewModels.MainViewModelFactory
import com.example.fleet.presentation.activities.NotificationActivity
import com.example.fleet.presentation.ui.theme.FleetTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
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

                val mainViewModel = ViewModelProvider(this, MainViewModelFactory(db))[MainViewModel::class.java]
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
                    db.settingsDao().upsert(settings1)
                    val settings = MutableStateFlow(db.settingsDao().get().first())
                    Log.i("aaa", settings.toString())

                }

                runBlocking {
                    delay(4000)
                }
                Text(text = mainViewModel.settings.collectAsState().value.toString())

                //Navigator(NotificationActivity())
            }
        }
    }
}