package com.example.network.model.response.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckEmployeePermissionResponseDto(
    @SerialName("message")
    val permissionGranted: Boolean,
)
