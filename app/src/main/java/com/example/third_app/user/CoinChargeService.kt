package com.example.third_app.user

import com.example.third_app.home.LikedItemFullImage
import com.example.third_app.login.Login
import retrofit2.Call
import retrofit2.http.*

interface CoinChargeService {
    @FormUrlEncoded
    @PUT("/user/{userId}/budget") //어떤 형태로 데이터를 전송할 것인가
    fun requestCoin(
        @Path("userId",encoded=true) id:String, //id String인 이유?
//        @Field("budget") budget : String
        @Field("newValue") newValue: Int
    ) : Call<CoinCharge> // 어떤 형태로 데이터를 받을 것인가 res
}