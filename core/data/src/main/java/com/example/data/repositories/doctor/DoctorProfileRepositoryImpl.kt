package com.example.data.repositories.doctor

import com.example.data.mapper.doctor.toDoctorProfileResponse
import com.example.data.mapper.employee_profile.toEmployeeProfileResponse
import com.example.domain.repositories.profile.EmployeeProfileRepository
import com.example.domain.repositories.profile.DoctorProfileRepository
import com.example.domain.repositories.local.UserPreferencesRepository
import com.example.model.doctor.doctor_profile.DoctorProfileResponse
import com.example.model.employee.EmployeeProfileResponse
import com.example.network.remote.doctor.profile.DoctorProfileApiService
import com.example.network.remote.employee_profile.EmployeeProfileApiService
import com.example.utility.network.Result
import com.example.utility.network.map
import com.example.utility.network.rootError

class DoctorProfileRepositoryImpl(
    private val doctorProfileService: DoctorProfileApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
) : DoctorProfileRepository {
    override suspend fun getDoctorInfo(): Result<DoctorProfileResponse, rootError> =
        userPreferencesRepository.executeWithValidToken {token->
            doctorProfileService.getDoctorInfo(token)
                .map { doctorProfileResponseDto ->
                    doctorProfileResponseDto.toDoctorProfileResponse()
                }
        }
}