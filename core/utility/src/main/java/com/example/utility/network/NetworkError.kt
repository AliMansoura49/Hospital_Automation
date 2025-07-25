package com.example.utility.network

    enum class NetworkError : Error {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
        UNPROCESSABLE_ENTITY,
        GUARDIAN_ALREADY_ASSIGNED,
        WRONG_ID,
        EMPTY_TOKEN,
        BAD_REQUEST,
        INVALID_PARAMETERS
    }

