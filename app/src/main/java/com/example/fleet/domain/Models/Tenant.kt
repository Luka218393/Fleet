package com.example.fleet.domain.Models

data class Tenant(
    val id: Int,
    var name: String,
    var age: Int,
    var address: String,
    val phone: String,
    var email: String,
    var gender: String,
    var profileImageRes: Int,
    val birthday: String,
    val createdAt: String,
    var profession: String,
    var aboutMe: String,
    var isOnline: Boolean,
    var tasksCompleted: List<Task>,
    var isApartmentHead: Boolean,
    var isTenantLeader: Boolean,
)