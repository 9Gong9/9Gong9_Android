package com.example.third_app

import retrofit2.Call
import org.json.JSONObject
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LogoutService {
//    @FormUrlEncoded
    @PUT("/user/{id}/logout")
    fun requestLogout(
        @Path("id") id:String
    ): Call<Login>
}
