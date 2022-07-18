package com.example.third_app.home

import android.provider.Telephony
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface GiveStarFullImageService {
    //INPUT 서비스
//    @FormUrlEncoded //인코딩 옵션
    @PUT("/item/rate/{itemId}") //어떤 형태로 데이터를 전송할 것인가
    fun requestGiveStar(
        @Path("itemId") itemId:String, //req
        @Query("userId") userId:String?,
        @Query("rate") usersRate: Float
    ) : Call<GiveStarFullImage> // 어떤 형태로 데이터를 받을 것인가 res

}