package com.example.third_app.home

import retrofit2.Call
import retrofit2.http.GET

interface AddressSearchService {
    @GET("/item/regionList") //어떤 형태로 데이터를 전송할 것인가
    fun requestRegionList(
    ) : Call<RegionData> // 어떤 형태로 데이터를 받을 것인가 res
}