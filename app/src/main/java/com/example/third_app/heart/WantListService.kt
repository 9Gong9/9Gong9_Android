package com.example.third_app.heart

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WantListService {
    //@FormUrlEncoded //인코딩 옵션
    @GET("/item/list/{userId}/ongoing") //어떤 형태로 데이터를 전송할 것인가
    fun requestWantList(
        @Path("userId", encoded=true) userId:String
    ) : Call<WantList> // 어떤 형태로 데이터를 받을 것인가 res
}