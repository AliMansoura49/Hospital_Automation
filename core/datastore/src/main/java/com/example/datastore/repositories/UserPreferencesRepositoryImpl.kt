package com.example.datastore.repositories

import android.util.Base64
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.datastore.constants.DataStoreKeys
import com.example.datastore.model.UserPreferences
import com.example.datastore.utility.Crypto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {
    override val userPreferencesFlow: Flow<UserPreferences>
        get() = dataStore.data
            .catch { cause ->
                Log.e("Error Reading UserPreferences",cause.message.toString())
                emit(emptyPreferences())
            }
            .map { preferences ->
                val isDarkTheme = preferences[DataStoreKeys.IS_DARK_THEME] == true
                val showPermissionCard = preferences[DataStoreKeys.SHOW_PERMISSION_CARD] == true

                val encrypted = preferences[DataStoreKeys.AUTH_TOKEN] ?: ""
                val token = if (encrypted.isEmpty()) {
                    null
                } else {
                    val encryptedBytes = Base64.decode(encrypted, Base64.DEFAULT)
                    val decrypted = Crypto.decrypt(encryptedBytes)
                    String(decrypted, Charsets.UTF_8)
                }

                UserPreferences(
                    isDarkTheme = isDarkTheme,
                    showPermissionCard = showPermissionCard,
                    token = token,
                )
            }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        try {
            dataStore.edit { preferences ->
                preferences[DataStoreKeys.IS_DARK_THEME] = isDarkTheme
            }
        } catch (e: Exception) {
            e.localizedMessage?.let {
                Log.e("UpdatingDarkTheme Error", it)
            }
        }
    }

    override suspend fun updateShowPermissionCard(showPermissionCard: Boolean) {
        try {
            dataStore.edit { preferences ->
                preferences[DataStoreKeys.SHOW_PERMISSION_CARD] = showPermissionCard
            }
        } catch (e: Exception) {
            e.localizedMessage?.let {
                Log.e("UpdatingShowPermissionCard Error", it)
            }
        }
    }


    override suspend fun updateToken(token: String) {
        try {
            dataStore.edit {
                val bytes = token.toByteArray()
                val encryptedBytes = Crypto.encrypt(bytes)
                val encryptedBytesBase64 = Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
                it[DataStoreKeys.AUTH_TOKEN] = encryptedBytesBase64
            }
        } catch (e: Exception) {
            e.localizedMessage?.let {
                Log.e("UpdatingToken Error", it)
            }
        }
    }
}