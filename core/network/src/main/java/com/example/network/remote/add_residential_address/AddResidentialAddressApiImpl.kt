package com.example.network.remote.add_residential_address

import android.util.Log
import com.example.datastore.repositories.UserPreferencesRepository
import com.example.network.model.request.AddressRequest
import com.example.network.model.response.AddAddressResponse
import com.example.network.utility.ApiRoutes
import com.example.utility.network.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first
import com.example.utility.network.Result
import com.example.utility.network.rootError

class AddResidentialAddressApiImpl(
    private val client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
) : AddResidentialAddressApi {
    override suspend fun addResidentialAddress(
        addressRequest: AddressRequest
    ): Result<AddAddressResponse, rootError> = try {
        val response = client.post(ApiRoutes.ADD_RESIDENTIAL_ADDRESS) {
            contentType(ContentType.Application.Json)
            val token = userPreferencesRepository.userPreferencesFlow.first().token
            if (token==null){
                return@post
                Log.e("Token","Token is null")
            }
            bearerAuth(token)
            setBody(addressRequest)
        }
        when (response.status.value) {
            in 200..299 -> {
                val addressResponse: AddAddressResponse = response.body()
                Log.v("AddResidentialAddressApi: in of range 2xx", addressResponse.updatedData.toString())
                Result.Success(data = addressResponse)
            }

            else -> {
                val errorBody=response.bodyAsText()
                Log.e("AddResidentialAddressApi: Out of range 2xx", errorBody)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    } catch (e: Exception) {
        Log.e("AddResidentialAddressApi Exception:", e.localizedMessage ?: "Unknown Error")
        Result.Error(NetworkError.UNKNOWN)
    }
}