package com.example.third_app.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_app.R
import com.example.third_app.databinding.ActivityAddressSearchBinding
import com.example.third_app.databinding.ActivityFoodFullImageBinding
import com.example.third_app.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressSearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddressSearchBinding
//    private lateinit var guAdapter : GuRecyclerAdapter
//    private lateinit var dongAdapter : GuRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gu_datalist = mutableListOf<String>()
        var dong_datalist = mutableListOf<String>()
        var regionData : RegionData?


        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 지역구 리스트
        val gu_list = mutableListOf("대덕구", "동구", "서구", "대덕구", "중구")

//        guAdapter = GuRecyclerAdapter()
//        guAdapter.datalist = gu_list
        binding.guRecyclerView.layoutManager = LinearLayoutManager(this@AddressSearchActivity)
//        binding.guRecyclerView.adapter = guAdapter //리사이클러뷰에 어댑터 연결

       var addressSearchService : AddressSearchService = retrofit.create(AddressSearchService::class.java)
        addressSearchService.requestRegionList().enqueue(object: Callback<RegionData>{
            override fun onFailure(call: Call<RegionData>, t: Throwable) {
                Log.e("RegionList","error : regionList 호출 실패")
//                dongAdapter = GuRecyclerAdapter()
//                binding.dongRecyclerView.adapter = dongAdapter //리사이클러뷰에 어댑터 연결
                binding.dongRecyclerView.layoutManager = LinearLayoutManager(this@AddressSearchActivity)
            }
            override fun onResponse(call: Call<RegionData>, response: Response<RegionData>) {
                regionData = response.body()
                Log.d("RegionList", "statusCode : "+regionData?.statusCode.toString())
                Log.d("RegionList", "Msg : "+regionData?.statusMsg.toString())
                if(regionData != null){
//
//                    dong_datalist = regionData?.data?.대전광역시
                }
            }



        })

    }
}