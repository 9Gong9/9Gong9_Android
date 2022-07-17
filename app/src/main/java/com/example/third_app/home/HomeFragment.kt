package com.example.third_app.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_app.*
import com.example.third_app.databinding.FragmentHomeBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter : FoodRecyclerViewAdapter //adapter객체 먼저 선언해주기!

    var mDatas=mutableListOf<Product>()
    var itemList: ItemList?=null

    // Context를 액티비티로 형변환해서 할당
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //itemListService
        var itemListService: ItemListService = retrofit.create(ItemListService::class.java)
        itemListService.requestItemList("대전광역시/유성구/신성동").enqueue(object :Callback<ItemList>{
            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                Log.e("ItemList","error : itemList 호출 실패")
                adapter = FoodRecyclerViewAdapter()
                binding.foodRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
                binding.foodRecyclerView.layoutManager = LinearLayoutManager(mainActivity)
            }

            override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                itemList=response.body()
                Log.d("ItemList", itemList.toString())
                Log.d("ItemList", "statusCode : "+itemList?.statusCode.toString())
                Log.d("ItemList", "Msg : "+itemList?.statusMsg.toString())
                adapter = FoodRecyclerViewAdapter()
                if(itemList!=null){
                    mDatas = itemList?.data?.resultItemList as MutableList<Product>
                }
                adapter.datalist = mDatas
                binding.foodRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
                binding.foodRecyclerView.layoutManager = GridLayoutManager(mainActivity, 2)
                adapter.notifyDataSetChanged()
                adapter.setItemClickListener(object : FoodRecyclerViewAdapter.OnItemClickListener {
                    override fun onClick(v: View, position: Int) {
                        //클릭 시 이벤트 작성
                        Toast.makeText(
                            context, "${mDatas[position].id} 클릭 이벤트.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(context, FoodFullImage::class.java)
                        intent.putExtra("itemId", mDatas[position].id)
                        startActivity(intent)
                    }
                })
            }

        })



        return binding.root
    }

}



