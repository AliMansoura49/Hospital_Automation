package com.example.network.remote.upload_employee_profile_image

import android.net.Uri
import com.example.network.model.response.ProgressUpdate
import kotlinx.coroutines.flow.Flow

interface UploadEmployeeProfileImageApi {
    fun uploadImage(uri: Uri): Flow<ProgressUpdate>
}