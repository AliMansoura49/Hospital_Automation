package com.example.domain.repositories.profile

import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

interface DoctorProfileRepository {
    suspend fun getDoctorInfo(): Result<DoctorProfileResponse, rootError>
}