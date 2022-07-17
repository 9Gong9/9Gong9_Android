package com.example.third_app.login

import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path

interface LogoutService {
//    @FormUrlEncoded
    @PUT("/user/{id}/logout")
    fun requestLogout(
        @Path("id") id:String
    ): Call<Login>
}
