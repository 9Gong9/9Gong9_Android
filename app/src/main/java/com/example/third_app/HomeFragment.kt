package com.example.third_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.third_app.databinding.FragmentHomeBinding
import java.io.Serializable
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter:FoodRecyclerViewAdapter //adapter객체 먼저 선언해주기!

    val mDatas=mutableListOf<FoodData>()
    val userId="1"
    val userName="jina"


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
//        binding.foodRecyclerView.
        this.initializelist()
        initFoodRecyclerView()
        adapter.setItemClickListener(object:FoodRecyclerViewAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                //클릭 시 이벤트 작성
                Toast.makeText(context,"${mDatas[position].food_name}\n${mDatas[position].food_price}원입니다.",
                Toast.LENGTH_SHORT).show()
                val intent= Intent(context, FoodFullImage::class.java)
                intent.putExtra("userId", userId)
                intent.putExtra("userName", userName)
                //intent.putExtra("foodData",mDatas!![position])
                startActivity(intent)
//                dataPassListener.onUserIDPass(userId)
//                dataPassListener.onUserNamePass(userName)
//                dataPassListener.OnDataPass(mDatas[position])

            }
        })
        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew1","20%","1680"))
        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew2","20%","1680"))
        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew3","20%","1680"))
        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew4","20%","1680"))

//        adapter.setOnItemClickListener(object : FoodRecyclerViewAdapter.OnItemClickListener{
//            override fun onItemClick(v: View, data: FoodData, pos : Int) {
//                Intent(this@HomeFragment, FoodFullImage::class.java).apply {
//                    putExtra("data", data)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run { startActivity(this) }
//            }
//
//        })
//        return inflater.inflate(R.layout.fragment_home, container, false)

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
//        val adapter=FoodRecyclerViewAdapter() //어댑터 객체 만듦
        adapter=FoodRecyclerViewAdapter() //어댑터 객체 만듦
//        adapter=FoodRecyclerViewAdapter {position->onItemClick(position as Int)} //어댑터 객체 만듦
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
    //clickListener func
//    private fun onItemClick(position: Int) {
//        Toast.makeText(this, mDatas[position], Toast.LENGTH_SHORT).show()
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //interface 생성
//    interface OnDataPassListener{
//        fun onUserIDPass(data: String?)
//        fun onUserNamePass(data:String?)
//        fun OnDataPass(data: FoodData)
//    }
//    lateinit var dataPassListener: OnDataPassListener
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        dataPassListener=context as OnDataPassListener
//    }
}