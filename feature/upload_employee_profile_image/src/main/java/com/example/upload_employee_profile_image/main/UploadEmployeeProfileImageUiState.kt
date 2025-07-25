package com.example.upload_employee_profile_image.main

import android.net.Uri
import com.example.constants.enums.FileUploadingState
import com.example.model.FileInfo
import com.example.util.UiText

data class UploadEmployeeProfileImageUiState(
    val uri: Uri? = null,
    val imageFileInfo: FileInfo? = null,
    val uploadingState: FileUploadingState= FileUploadingState.CANCELLED,
    val isUploadButtonEnabled: Boolean = false,
    val showErrorDialog: Boolean = false,
    val errorDialogText: UiText? = null,
)
