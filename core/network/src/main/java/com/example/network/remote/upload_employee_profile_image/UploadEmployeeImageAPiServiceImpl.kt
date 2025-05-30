package com.example.network.remote.upload_employee_profile_image

import android.net.Uri
import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.constants.Role
import com.example.network.model.response.ProgressUpdate
import com.example.network.utility.ApiRoutes
import com.example.network.utility.file.FileReader
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first


class UploadEmployeeProfileImageApiImpl(
    private val client: HttpClient,
    private val fileReader: FileReader,
    private val userPreferencesRepository: UserPreferencesRepository,
) : UploadEmployeeProfileImageApi {
    override fun uploadImage(uri: Uri): Flow<ProgressUpdate> = channelFlow {
        val info = fileReader.uriToFileInfo(uri)

        val token = userPreferencesRepository.userPreferencesFlow.first().token

        if (token.isNullOrBlank()) {
            close(IllegalStateException("Bearer token is not available."))
            return@channelFlow
        }

        println("AuthToken: $token")

        try {
            val response = client.submitFormWithBinaryData(
                url = ApiRoutes.UPLOAD_EMPLOYEE_PROFILE_IMAGE,
                formData = formData {
                    append(
                        "request_type",
                        Role.EMPLOYEE.name.lowercase()
                    )
                    append(
                        "image",
                        info.bytes,
                        Headers.build {
                            append(HttpHeaders.ContentType, info.mimeType)
                            append(HttpHeaders.ContentDisposition, "filename=\"${info.name}\"")
                        }
                    )
                    Log.v("Uploading Image", "Client")
                }
            ) {
                method = HttpMethod.Post
                bearerAuth(token)
                onUpload { bytesSent, totalBytes ->
                    if ((totalBytes ?: 0) > 0L) {
                        send(ProgressUpdate(bytesSent, totalBytes ?: 0))
                        Log.v("Uploading Image", "$bytesSent/$totalBytes")
                    }
                }
            }
            when (response.status.value) {
                in 200..299 -> {
                    Log.v("Uploading Image", "Server responded with success: ${response.status}")
                }

                else -> {
                    val errorBody = response.body<String>()
                    Log.e(
                        "Uploading Image",
                        "Server responded with error: ${response.status}, Body: $errorBody"
                    )
                    close(Exception("Upload failed on server: ${response.status} - $errorBody"))
                }
            }
        } catch (e: Exception) {
            Log.e("Uploading Image Exception", ": ${e.message}")
            close(Exception("Upload failed on server: ${e.message}"))
        }
    }
}
