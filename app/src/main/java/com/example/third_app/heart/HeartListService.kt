package com.example.third_app.heart

import com.example.third_app.home.ItemList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeartListService {
    //@FormUrlEncoded //인코딩 옵션
    @GET("/item/list/{userId}/likers") //어떤 형태로 데이터를 전송할 것인가
    fun requestItemList(
        @Path("userId", encoded=true) userId:String,
    ) : Call<HeartList> // 어떤 형태로 데이터를 받을 것인가 res
}