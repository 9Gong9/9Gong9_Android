package com.example.third_app.user

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PreviousItemListService {
    //@FormUrlEncoded //인코딩 옵션
    @GET("/item/list/{userid}/previous") //어떤 형태로 데이터를 전송할 것인가
    fun requestItemList(
        @Path("userid", encoded=true) userid:String
    ) : Call<PreviousItemList> // 어떤 형태로 데이터를 받을 것인가 res
}