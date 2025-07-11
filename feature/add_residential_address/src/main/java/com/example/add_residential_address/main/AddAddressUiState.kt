package com.example.add_residential_address.main

import com.example.model.enums.ScreenState
import com.example.util.UiText


data class AddAddressUiState(
    val governorate: String = "",
    val governorateError: UiText? = null,
    val city: String = "",
    val cityError: UiText? = null,
    val region: String = "",
    val regionError: UiText? = null,
    val street: String = "",
    val streetError: UiText? = null,
    val note: String = "",
    val noteError: UiText? = null,
    val isAddressInfoPolicyChecked: Boolean = false,
    val isSubmitButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val screenState: ScreenState= ScreenState.IDLE,
)