package com.example.third_app.home

import retrofit2.Call
import retrofit2.http.*

interface LikedFoodFullImageService {
//    @FormUrlEncoded
    @PUT("/item/like/{userid}/{itemid}") //어떤 형태로 데이터를 전송할 것인가
    fun requestUpdateHeartList(
        @Path("itemid",encoded=true) id:String,
        @Path("userid") userid : String
    ) : Call<LikedItemFullImage> // 어떤 형태로 데이터를 받을 것인가 res
}