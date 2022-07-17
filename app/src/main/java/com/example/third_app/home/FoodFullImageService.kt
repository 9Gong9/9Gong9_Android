package com.example.third_app.home

import com.example.third_app.user.PreviousItemList
import retrofit2.Call
import retrofit2.http.*

interface FoodFullImageService {
//    @FormUrlEncoded //인코딩 옵션
    @GET("/item/{itemid}") //어떤 형태로 데이터를 전송할 것인가
    fun requestItemList(
        @Path("itemid", encoded=true) id:String,
        @Query("userId") userid : String
    ) : Call<ItemFullImage> // 어떤 형태로 데이터를 받을 것인가 res
}