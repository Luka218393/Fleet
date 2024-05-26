package com.example.fleet.domain.Models

import com.example.fleet.domain.Enums.FontSize
import com.example.fleet.domain.Enums.Language
import com.example.fleet.domain.Enums.Theme

data class Settings (
    val id: Int = 0,
    val tenantId: Int = 0,
    //var tags = List<Tag>
    val buildingId: Int = 0,
    val apartmentId: Int = 0,
    var theme: Theme = Theme.DEFAULT,
    var font: FontSize = FontSize.MEDIUM,
    var showAnimation: Boolean = true,
    var language: Language = Language.ENGLISH,
    var showNotifications: Boolean = true
)

data class SettingsState (
    val id: Int = 0,
    val tenant: Tenant,
    //var tags = List<Tag>
    val building: Building,
    val apartment: Apartment,
    var theme: Theme = Theme.DEFAULT,
    var font: FontSize = FontSize.MEDIUM,
    var showAnimation: Boolean = true,
    var language: Language = Language.ENGLISH,
    var showNotifications: Boolean = true
)