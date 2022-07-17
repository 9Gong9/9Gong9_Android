package com.example.third_app.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemListService {
    //@FormUrlEncoded //인코딩 옵션
    @GET("/item/list/{region}") //어떤 형태로 데이터를 전송할 것인가
    fun requestItemList(
        @Path("region", encoded=true) id:String
    ) : Call<ItemList> // 어떤 형태로 데이터를 받을 것인가 res
}