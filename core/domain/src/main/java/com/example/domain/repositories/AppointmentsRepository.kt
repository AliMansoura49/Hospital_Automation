package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.UpdatedIds
import kotlinx.coroutines.flow.Flow

interface AppointmentsRepository {
    suspend fun getAppointments(
        appointmentState: AppointmentState,
        onStatisticsChanged: (AppointmentsStatisticsData)-> Unit,
        queryFilter: String?,
        dateFilter: String?
    ): Flow<PagingData<AppointmentData>>

    suspend fun getAppointmentDetails(
        id: Int
    ) : Result<AppointmentData,NetworkError>

    suspend fun updateAppointmentStateToPassed(appointmentId: Int): Result<UpdatedIds, NetworkError>
    suspend fun updateAppointmentStateToMissed(appointmentId: Int): Result<UpdatedIds, NetworkError>


    /**
     * adds a medical diagnosis related by the appointment.
     * returns the number of affected rows .
     * @author Ali Mansoura.
     */
    suspend fun addDiagnosis(
        appointmentId: Int,
        diagnosis: String
    ): Result<Int, NetworkError>
}