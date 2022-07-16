package com.example.third_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.third_app.databinding.FragmentHomeBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter:FoodRecyclerViewAdapter //adapter객체 먼저 선언해주기!

    val mDatas=mutableListOf<FoodData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        setContentView(binding.root)
        this.initializelist()
        initFoodRecyclerView()
        // return inflater.inflate(R.layout.fragment_home, container, false)

//        // 레트로핏 객체 생성
//        var retrofit = Retrofit.Builder()
//            .baseUrl("http://192.249.18.195:80")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // ItemList 서비스 올리기
//        var itemListService: ItemListService = retrofit.create(ItemListService::class.java)
//
//        itemListService.requestItemList().enqueue(object: Callback<ItemList> {
//            override fun onFailure(call: Call<ItemList>, t: Throwable) {
//
//            }
//            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
//                if (response.isSuccessful){
//                    val body = response.body()
//                    body?.let{
//
//                    }
//                }
//            }
//        })


        return binding.root
    }

    private fun setContentView(root: FrameLayout) {
    }

    private fun initFoodRecyclerView() {
        adapter=FoodRecyclerViewAdapter() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.foodRecyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
        // binding.foodRecyclerView.layoutManager= LinearLayoutManager(this.context) //레이아웃 매니저 연결
        binding.foodRecyclerView.layoutManager= GridLayoutManager(this.context,2)
    }

    private fun initializelist() {
        with(mDatas){
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water1","20%","1980"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water2","30%","1200"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water3","40%","1280"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water4","20%","4500"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water5","20%","3400"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water6","30%","1245"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water7","60%","1500"))
            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","water8","20%","1680"))
        }
    }

}