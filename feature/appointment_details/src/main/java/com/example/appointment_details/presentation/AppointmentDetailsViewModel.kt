package com.example.appointment_details.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.doctor.appointment.GetAppointmentDetailsUseCase
import com.example.domain.use_cases.doctor.appointment.UpdateAppointmentStateToMissedUseCase
import com.example.domain.use_cases.doctor.appointment.UpdateAppointmentStateToPassedUseCase
import com.example.model.doctor.appointment.AppointmentData
import com.example.model.doctor.appointment.AppointmentState
import com.example.model.enums.BottomBarState
import com.example.model.enums.ScreenState
import com.example.ui_components.R
import com.example.util.UiText
import com.example.utility.constants.DurationConstants
import com.example.utility.network.NetworkError
import com.example.utility.network.Result
import com.example.utility.network.UpdatedIds
import com.example.utility.network.onError
import com.example.utility.network.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppointmentDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val updateAppointmentStateToMissed: UpdateAppointmentStateToMissedUseCase,
    private val updateAppointmentStateToPassed: UpdateAppointmentStateToPassedUseCase,
    private val getAppointmentDetails: GetAppointmentDetailsUseCase
): ViewModel() {


    private val _uiState = MutableStateFlow(AppointmentDetailsUIState())
    val uiState : StateFlow<AppointmentDetailsUIState> = _uiState
        .onStart {
            initValues()
            loadDate()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AppointmentDetailsUIState()
        )

    private fun initValues(){
        val id = 1
        _uiState.value = _uiState.value.copy(appointmentId = id)
    }

    fun onAction(action: AppointmentDetailsAction){
        when(action){
            AppointmentDetailsAction.MarkAsMissed ->markAsMissed()
            AppointmentDetailsAction.MarkAsPassed -> markAsPassed()
            AppointmentDetailsAction.ClearToastMessage -> {
                _uiState.value = _uiState.value.copy(toastMessage = null)
            }
            AppointmentDetailsAction.Refresh -> refreshData()
            is AppointmentDetailsAction.ShowToast -> showToast(action.message)
        }
    }
    private fun updateState(newState: ScreenState){
        _uiState.value = _uiState.value.copy(screenState = newState)
    }
    fun loadDate()= viewModelScope.launch{
        updateState(ScreenState.LOADING)
        Log.d("AppointmentViewModel", uiState.value.screenState.name)
        val result : Result<AppointmentData, NetworkError> = getAppointmentDetails(uiState.value.appointmentId)
        result.onSuccess {data->
            updateState(ScreenState.SUCCESS)
            Log.d("AppointmentViewModel", uiState.value.screenState.name)
            _uiState.value = _uiState.value.copy(appointment = data)
        }.onError {
            updateState(ScreenState.ERROR)
            Log.d("AppointmentViewModel", uiState.value.screenState.name)
        }
    }
    private fun updateRefreshState(isRefreshing: Boolean){
        _uiState.value = _uiState.value.copy(isRefreshing=isRefreshing)
    }
    private fun showToast(message: UiText){
        _uiState.value = _uiState.value.copy(toastMessage = message)
    }
    private fun updateAppointment(appointment: AppointmentData){
        _uiState.value = _uiState.value.copy(appointment=appointment)
    }
    private fun refreshData(){
        viewModelScope.launch{
            updateRefreshState(true)
            getAppointmentDetails(uiState.value.appointmentId)
                .onSuccess{ result->
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.data_updated_successfully))
                    updateAppointment(result)
                    if(uiState.value.screenState == ScreenState.ERROR){
                        updateState(ScreenState.SUCCESS)
                    }
                }.onError {
                    updateRefreshState(false)
                    showToast(UiText.StringResource(R.string.something_went_wrong))
                }
        }
    }
    private fun updateMarkAsMissedButtonState(newState: BottomBarState){
        _uiState.value = _uiState.value.copy(markAsMissedButtonState = newState)
    }
    private fun updateMarkAsPassedButtonState(newState: BottomBarState){
        _uiState.value = _uiState.value.copy(markAsPassedButtonState = newState)
    }
    private fun markAsPassed() = viewModelScope.launch {
        updateMarkAsPassedButtonState(BottomBarState.LOADING)
        if(uiState.value.appointment?.state == AppointmentState.PASSED){
            showToast(UiText.StringResource(R.string.already_passed))
            updateMarkAsPassedButtonState(BottomBarState.SUCCESS)
            return@launch
        }
        val result: Result<UpdatedIds, NetworkError> = updateAppointmentStateToPassed(
            _uiState.value.appointmentId
        )
        result.onSuccess {
            updateMarkAsPassedButtonState(
                BottomBarState.SUCCESS
            )
        }
        result.onError {
            updateMarkAsPassedButtonState(
                BottomBarState.FAILURE
            )
            delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
            updateMarkAsPassedButtonState(
                BottomBarState.IDLE
            )
        }
    }
    private fun markAsMissed() = viewModelScope.launch {
        updateMarkAsMissedButtonState(BottomBarState.LOADING)
        if(uiState.value.appointment?.state == AppointmentState.MISSED){
            showToast(UiText.StringResource(R.string.already_missed))
            updateMarkAsMissedButtonState(BottomBarState.SUCCESS)
            return@launch
        }
        val result: Result<UpdatedIds, NetworkError> = updateAppointmentStateToMissed(
            _uiState.value.appointmentId
        )
        result.onSuccess {
            updateMarkAsMissedButtonState(
                BottomBarState.SUCCESS
            )
        }
        result.onError {
            updateMarkAsPassedButtonState(
                BottomBarState.FAILURE
            )
            delay(DurationConstants.BUTTON_ERROR_STATE_DURATION)
            updateMarkAsPassedButtonState(
                BottomBarState.IDLE
            )
        }
    }
}