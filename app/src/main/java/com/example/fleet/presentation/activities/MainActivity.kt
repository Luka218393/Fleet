package com.example.fleet.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.navigator.Navigator
import com.example.fleet.AppInstances
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Navigation
import com.example.fleet.domain.viewModels.MainViewModel
import com.example.fleet.domain.viewModels.MainViewModelFactory
import com.example.fleet.domain.viewModels.NotificationViewModel
import com.example.fleet.domain.viewModels.NotificationViewModelFactory
import com.example.fleet.presentation.ui.theme.FleetTheme

/**
This is the starting point of the app.
*/
class MainActivity : ComponentActivity() {


    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FleetTheme {
                val db = FleetDatabase.getDatabase(context = this)

                val mainViewModel = ViewModelProvider(this, MainViewModelFactory(db))[MainViewModel::class.java]
                val notificationViewModel = ViewModelProvider(this, NotificationViewModelFactory(db, mainViewModel.settings))[NotificationViewModel::class.java]



                val appInstances = AppInstances(notificationViewModel)
                val navigation = Navigation(appInstances)
                /*runBlocking {
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

                }*/

                //Text(text = mainViewModel.settings.value.buildingId.toString())
                //Text(text = mainViewModel.tenant.collectAsState(initial = "").value.id.toString())

                Navigator(NotificationActivity(viewModel = notificationViewModel, navigation = navigation))
            }
        }
    }
}