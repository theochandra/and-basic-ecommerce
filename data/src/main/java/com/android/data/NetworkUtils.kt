package com.android.data

import android.util.Log
import com.android.domain.Result

suspend fun <T: Any> safeApiCall(
    call: suspend () -> Result<T>,
    errorMessage: String
) : Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Log.e("MyTAG", "Exception : $e")
        Result.Exception(Exception(errorMessage, e))
    }
}