package com.example.third_app

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
import com.example.third_app.databinding.FragmentHeartBinding
import com.example.third_app.heart.HeartListService
import com.example.third_app.heart.HeartRecyclerViewAdapter
import com.example.third_app.heart.HeartList
import com.example.third_app.home.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HeartFragment : Fragment() {//찜목록과 원하는 목록 구분 필요******************
    private lateinit var binding: FragmentHeartBinding
    private lateinit var adapter : HeartRecyclerViewAdapter //adapter객체 먼저 선언해주기!
//    lateinit var viewModel : ItemViewModel by activityViewModels()

    var mDatas=mutableListOf<Product>()
    var heartList: HeartList?=null

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
        binding = FragmentHeartBinding.inflate(inflater, container, false)

        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var userid = sharedManager.getCurrentUser().id

        //itemListService
        var heartListService: HeartListService = retrofit.create(HeartListService::class.java)
        heartListService.requestItemList(userid).enqueue(object :Callback<HeartList>{
            override fun onFailure(call: Call<HeartList>, t: Throwable) {
                Log.e("HeartList","error : HeartList 호출 실패")
                adapter = HeartRecyclerViewAdapter()
                binding.heartRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
                binding.heartRecyclerView.layoutManager = LinearLayoutManager(mainActivity)
            }

            override fun onResponse(call: Call<HeartList>, response: Response<HeartList>) {
                heartList=response.body()
                Log.d("HeartList", heartList.toString())
                Log.d("HeartList", "statusCode : "+heartList?.statusCode.toString())
                Log.d("HeartList", "Msg : "+heartList?.statusMsg.toString())
                adapter = HeartRecyclerViewAdapter()
                if(heartList!=null){
                    mDatas = heartList?.data?.usersLikedItem as MutableList<Product>
                }
                adapter.datalist = mDatas
                binding.heartRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
                binding.heartRecyclerView.layoutManager = GridLayoutManager(mainActivity, 2)
                adapter.notifyDataSetChanged()
                adapter.setItemClickListener(object : HeartRecyclerViewAdapter.OnItemClickListener {
                    override fun onClick(v: View, position: Int) {
                        //클릭 시 이벤트 작성
                        Toast.makeText(
                            context, "${mDatas[position].id} 클릭 이벤트.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // 전역변수 itemId

                        //setFragmentResult()
                        val intent = Intent(mainActivity, FoodFullImageActivity::class.java).apply{
//                            putExtra("itemId", mDatas[position].id.toString())
                        }
                        ItemApplication.setItemId(mDatas[position].id.toString())
//                        viewModel.itemId = mDatas[position].id.toString()
                        startActivity(intent)
//                        val intent = Intent(context, FoodFullImage::class.java).apply{
//                            putExtra("itemId", mDatas[position].id.toString())
//                        }
//                        setResult(RESULT_OK,intent)
                        Log.e("Heart: ", mDatas[position].id.toString())
                    }
                })
            }

        })
        return binding.root
    }
}