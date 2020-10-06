package com.karis.videoozone.data.network


import com.karis.videoozone.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> safeApiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val responseErr = response.errorBody()?.string()
            val message = StringBuilder()
            responseErr?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                }
            }
            throw ApiException(message.toString())
        }
    }

}