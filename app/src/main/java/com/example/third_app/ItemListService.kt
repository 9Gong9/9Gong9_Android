package com.example.third_app

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ItemListService {
    @FormUrlEncoded //인코딩 옵션
    @GET("/item/list") //어떤 형태로 데이터를 전송할 것인가
    fun requestItemList() : Call<ItemList> // 어떤 형태로 데이터를 받을 것인가 res
}