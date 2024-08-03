package com.example.fleet.domain.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fleet.FleetApplication
import com.example.fleet.R
import com.example.fleet.data.FleetDatabase
import com.example.fleet.domain.Models.Apartment
import com.example.fleet.domain.Models.Tenant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.UUID

class CreationViewModel(
    private val db: FleetDatabase,
): ViewModel() {
    var buildingId = mutableStateOf("")
    var apartmentId = mutableStateOf("")

    var tenantId = mutableStateOf<String?>(null)
    var name = mutableStateOf("")
    var surname = mutableStateOf("")
    var email = mutableStateOf("")
    var phoneNumber = mutableStateOf("")
    var gender = mutableStateOf("")
    var birthday = mutableStateOf("")
    var profession = mutableStateOf("")
    var description = mutableStateOf("")
    var password = mutableStateOf("")
    var repeatedPassword = mutableStateOf("")

    fun areFieldsFilled():Boolean{
        return(
                name.value.isNotEmpty() &&
                surname.value.isNotEmpty() &&
                email.value.isNotEmpty() &&
                phoneNumber.value.isNotEmpty() &&
                gender.value.isNotEmpty() &&
                birthday.value.isNotEmpty() &&
                profession.value.isNotEmpty() &&
                description.value.isNotEmpty() &&
                password.value.isNotEmpty() &&
                repeatedPassword.value.isNotEmpty() &&
                password.value == repeatedPassword.value
                )
    }
    //Todo validate every field
    //Todo add proper apartment
    fun createTenant(){
        tenantId.value = UUID.randomUUID().toString()
        viewModelScope.launch {
            db.tenantDao().upsert(
                Tenant(
                    tenantId.value!!,
                    "2",
                    name.value,
                    surname.value,
                    phoneNumber.value,
                    email.value,
                    gender.value,
                    R.drawable.account_icon,
                    null,
                    profession.value,
                    description.value,
                    password = password.value
                )
            )
        }
        createSettings(tenantId.value!!)
    }
    //Todo make settings not delete and make changing accounts faster by this
    private fun createSettings(tenantId: String){
        viewModelScope.launch (Dispatchers.IO){
            db.settingsDao().upsert(
                FleetApplication.fleetModule.settings.value.copy(
                    tenantId = tenantId
                )
            )
        }
    }
    fun openSettings(){
        viewModelScope.launch(Dispatchers.IO) {
            val tenant = db.tenantDao().getByEmail(email.value)
            if (tenant.password == password.value){
                 db.settingsDao().upsert(
                     FleetApplication.fleetModule.settings.value.copy(
                         tenantId = tenant.id
                     )
                 )
            }
        }
    }

    fun buildingIdIsValid(id: String):Boolean = runBlocking (Dispatchers.IO) { db.buildingDao().exists(id) }

    fun getBuildingsApartments():List<Apartment> = runBlocking(Dispatchers.IO) {
        db.apartmentDao().getByBuildingId(buildingId.value)
    }

}

@Suppress("UNCHECKED_CAST")
class CreationViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreationViewModel::class.java)) {
            return CreationViewModel(FleetApplication.fleetModule.fleetDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}