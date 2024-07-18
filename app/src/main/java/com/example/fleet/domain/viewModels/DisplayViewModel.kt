package com.example.fleet.domain.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fleet.FleetApplication
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DisplayViewModel(
    private val db: FleetDatabase,
): ViewModel() {
    fun getTenantById(id: Int) = runBlocking { db.tenantDao().getById(id).first() ?: Tenant(0,"", apartmentId = 1) }

    var tenant by mutableStateOf<Tenant?>(null)
    var name = mutableStateOf("123")
    var surname = mutableStateOf("")
    var age = mutableIntStateOf(0)
    var description = mutableStateOf("")
    var profession = mutableStateOf("")
    var apartmentId = mutableIntStateOf(0)
    var phoneNumber = mutableStateOf("")
    var email = mutableStateOf("")
    var birthday = mutableStateOf("")
    var profileImageRes = mutableStateOf<Int?>(null)

    fun getTenantAttributes(tenantId: Int){
        tenant = getTenantById(tenantId)
        if (tenant != null) {
            name.value = tenant!!.name
            surname.value = tenant!!.surname
            age.intValue = tenant!!.age ?: 0
            description.value = tenant!!.aboutMe ?: ""
            profession.value = tenant!!.profession ?: ""
            apartmentId.intValue = tenant!!.apartmentId ?: 1
            phoneNumber.value = tenant!!.phoneNumber ?: ""
            email.value = tenant!!.email ?: ""
            birthday.value = tenant!!.birthday.toString() ?: ""
            profileImageRes.value = tenant!!.profileImageRes
            Log.i("DisplayViewModel", "getTenantAttributes")
        }
    }

    fun changeTenant(){
        val newTenant = tenant!!.copy(
            name = name.value,
            surname = surname.value,
            age = age.intValue,
            aboutMe = description.value,
            profession = profession.value,
            phoneNumber = phoneNumber.value,
            email = email.value,
        )
        runBlocking { db.tenantDao().upsert(newTenant) }
        Log.i("DisplayViewModel", "Change Tenant")

    }
}


@Suppress("UNCHECKED_CAST")
class DisplayViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayViewModel::class.java)) {
            return DisplayViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
