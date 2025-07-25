package com.example.network.model.dto.child

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildDto(
    @SerialName("childId") val childId: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("father_first_name") val fatherFirstName: String,
    @SerialName("father_last_name") val fatherLastName: String,
    @SerialName("mother_first_name") val motherFirstName: String,
    @SerialName("mother_last_name") val motherLastName: String,
    @SerialName("date_of_birth") val dateOfBirth: String,
    val gender: String,
    @SerialName("employee_id") val employeeId: Int? = null,
    val updatedAt: String,
    val createdAt: String,
    @SerialName("birth_certificate_img_url")
    val birthCertificateImgUrl: String? = null
)