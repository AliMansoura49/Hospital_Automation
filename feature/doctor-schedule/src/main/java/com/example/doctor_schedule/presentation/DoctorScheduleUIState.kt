package com.example.doctor_schedule.presentation

import com.example.model.doctor.appointment.AppointmentState
import com.example.model.doctor.appointment.AppointmentsStatisticsData
import com.example.model.enums.ScreenState
import java.time.LocalDate

data class DoctorScheduleUIState(
    val doctorId: Int,
    val isRefreshing: Boolean = false,
    val screenState: ScreenState = ScreenState.IDLE,
    val permissionsState: ScreenState = ScreenState.IDLE,
    val selectedTab: AppointmentState = AppointmentState.UPCOMMING,
    val isSearchBarVisible: Boolean = false,
    val isDatePickerVisible: Boolean = false,
    val selectedDate: LocalDate? = null,
    val isDrawerOpened: Boolean = false,
    val searchQuery: String = "",
    val statistics: AppointmentsStatisticsData = AppointmentsStatisticsData(),
    val isDarkTheme: Boolean = false,
    val isPermissionGranted: Boolean = false,
){
    fun hasDateFilter() = selectedDate!=null
}

