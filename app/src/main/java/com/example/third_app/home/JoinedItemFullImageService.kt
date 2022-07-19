package com.example.third_app.home

import com.example.third_app.login.Login
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT
import retrofit2.http.Path

interface JoinedItemFullImageService {
    //INPUT 서비스
//    @FormUrlEncoded //인코딩 옵션
    @PUT("/item/join/{userId}/{itemId}") //어떤 형태로 데이터를 전송할 것인가
    fun requestJoined(
        @Path("itemId") id: String?, //req
        @Path("userId") password:String?,
    ) : Call<JoinedItemFullImage> // 어떤 형태로 데이터를 받을 것인가 res


}