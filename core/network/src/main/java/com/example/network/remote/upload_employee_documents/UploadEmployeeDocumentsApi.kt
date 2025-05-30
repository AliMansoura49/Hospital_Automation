package com.example.network.remote.upload_employee_documents

import android.net.Uri
import com.example.network.model.response.ProgressUpdate
import kotlinx.coroutines.flow.Flow

interface UploadEmployeeDocumentsApi {
    fun uploadFile(uri: Uri): Flow<ProgressUpdate>
}