package com.example.datastore.service

import com.example.datastore.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesService {
    val userPreferencesDataStoreFlow: Flow<UserPreferences>

    suspend fun updateIsDarkTheme(isDarkTheme:Boolean)

    suspend fun updateShowPermissionCard(showPermissionCard:Boolean)

    suspend fun updateToken(token:String?)
}