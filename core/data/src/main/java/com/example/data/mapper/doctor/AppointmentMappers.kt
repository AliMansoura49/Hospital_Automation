package com.example.data.mapper.doctor

import com.example.data.mapper.child.toChildData
import com.example.data.mapper.enums.toAgeUnit
import com.example.data.mapper.user.toGuardianData
import com.example.data.mapper.vaccine.toVaccineInteraction
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentTypeData
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.doctor.clinic.ClinicData
import com.example.model.age.Age
import com.example.model.vaccine.VaccineData
import com.example.network.model.dto.doctor.appointment.AppointmentDto
import com.example.network.model.dto.doctor.appointment.AppointmentTypeDto
import com.example.network.model.dto.doctor.clinic.ClinicDto
import com.example.network.model.dto.vaccine.VaccineDto
import com.example.network.model.dto.doctor.appointment.AppointmentStatisticsDto

internal fun AppointmentDto.toAppointmentData() =
    AppointmentData(
        id = id,
        recommendedVisitDate = recommendedVisitDate,
        recommendedVisitNote = recommendedVisitNote,
        state = AppointmentState.getFromString(state),
        medicalDiagnosis = medicalDiagnosis,
        date = date,
        time = time,
        clinicId = clinicId,
        vaccineId = vaccineId,
        appointmentTypeId = appointmentTypeId,
        childId = childId,
        doctorId =doctorId,
        patientId = patientId,
        appointmentType = appointmentType.toAppointmentTypeData(),
        user = user?.toGuardianData(),
        vaccine = vaccine?.toVaccineData(),
        clinic = clinic.toClinicFullData(),
        child = child?.toChildData()
    )


internal fun AppointmentTypeDto.toAppointmentTypeData() =
    AppointmentTypeData(
        id = id,
        name =name ,
        standardDurationInMinutes = standardDurationInMinutes,
        description = description,
        doctorId = doctorId
    )
internal fun ClinicDto.toClinicFullData() =
    ClinicData(
        clinicId = clinicId,
        name = name
    )

internal fun VaccineDto.toVaccineData() =
    VaccineData(
        id = id,
        name = name,
        description = description,
        quantity = quantity,
        minAge = Age(minAge, minAgeUnit.toAgeUnit()),
        maxAge = Age(minAge, maxAgeUnit.toAgeUnit()),
        interactions = this.interactions?.map { it.toVaccineInteraction() }
    )
internal fun AppointmentStatisticsDto.toAppointmentsStatisticsData() =
    AppointmentsStatisticsData(
        upcoming = upcoming,
        passed = passed,
        missed = missed
    )