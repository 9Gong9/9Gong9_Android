package com.example.third_app.home

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_app.*
import com.example.third_app.category.CategoryApplication
import com.example.third_app.databinding.FragmentHomeBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter : FoodRecyclerViewAdapter //adapter객체 먼저 선언해주기!

    var mDatas=mutableListOf<Product>()
    var itemList: ItemList?=null
    // sharedManager 선언
    private val sharedManager : SharedManager by lazy { SharedManager(mainActivity) }

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

        var userid = sharedManager.getCurrentUser().id

        //itemListService
        var itemListService: ItemListService = retrofit.create(ItemListService::class.java)
        itemListService.requestItemList(RegionApplication.getRegion()+ "/"+CategoryApplication.getCategoryId(), userid).enqueue(object :Callback<ItemList>{
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
                    var itemNameList : Array<String> = itemList?.data?.foundNameList!!.toTypedArray()
                    // 검색어 자동 완성
                    var autoCompleteAdapter = ArrayAdapter(mainActivity, android.R.layout.simple_dropdown_item_1line,itemNameList)
                    binding.etHomeSearch.setAdapter(autoCompleteAdapter)
                    Log.e("auto", itemNameList.toString())
//                    bnding.etHomeSearch.

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
                        // 전역변수 itemId
                        //setFragmentResult()
                        val intent = Intent(mainActivity, FoodFullImageActivity::class.java).apply{
                            putExtra("itemId", mDatas[position].id.toString())
                        }
                        ItemApplication.setItemId(mDatas[position].id.toString())
                        startActivity(intent)
                        Log.e("Home", mDatas[position].id.toString())
                    }
                })
            }
        })

        binding.etHomeSearch.setOnKeyListener{ v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER){
//                // 키패드 내리기
//                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.hideSoftInputFromWindow(binding.etHomeSearch.windowToken, 0)
                var searchItemListService: SearchItemListService = retrofit.create(SearchItemListService::class.java)
                var searchKey = binding.etHomeSearch.text
                searchItemListService.requestItemList(RegionApplication.getRegion(),searchKey.toString(), userid).enqueue(object :Callback<ItemList>{
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
                                // 전역변수 itemId
                                //setFragmentResult()
                                val intent = Intent(mainActivity, FoodFullImageActivity::class.java).apply{
                                    putExtra("itemId", mDatas[position].id.toString())
                                }
                                ItemApplication.setItemId(mDatas[position].id.toString())
                                startActivity(intent)
                                Log.e("Home", mDatas[position].id.toString())
                            }
                        })
                    }
                })
                true
            }
            else{
                false
            }
        }
        return binding.root
    }

}



