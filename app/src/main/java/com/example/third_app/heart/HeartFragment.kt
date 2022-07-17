//package com.example.third_app
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import android.widget.Toast
//import androidx.databinding.DataBindingUtil.setContentView
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.third_app.databinding.FragmentHeartBinding
//import com.example.third_app.databinding.FragmentHomeBinding
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//
//
//
//class HeartFragment : Fragment() {
//    private lateinit var binding: FragmentHeartBinding
//    private lateinit var adapter: HeartRecyclerViewAdapter
////    private lateinit var adapter: FoodRecyclerViewAdapter //adapter객체 먼저 선언해주기!
//    val mDatas=mutableListOf<HeartData>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding=FragmentHeartBinding.inflate(inflater, container, false)
//        //setContentView(binding.root)
//        this.initializelist()
//        initHeartRecyclerView()
//        adapter.setItemClickListener(object : FoodRecyclerViewAdapter.OnItemClickListener {
//            override fun onClick(v: View, position: Int) {
//                //클릭 시 이벤트 작성
//                Toast.makeText(
//                    context, "${mDatas[position].food_name}\n${mDatas[position].food_price}원입니다.",
//                    Toast.LENGTH_SHORT
//                ).show()
//                val intent = Intent(context, FoodFullImage::class.java)
//                intent.putExtra("userId", userId)
//                intent.putExtra("userName", userName)
//                //intent.putExtra("foodData",mDatas!![position])
//                startActivity(intent)
//
//            }
//        })
////        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew1","20%","1680"))
////        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew2","20%","1680"))
////        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew3","20%","1680"))
////        mDatas.add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8","newnew4","20%","1680"))
//
//
//        // 레트로핏 객체 생성
//        var retrofit = Retrofit.Builder()
//            .baseUrl("http://192.249.18.195:80")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        return binding.root
//    }
//
//    private fun setContentView(root: FrameLayout) {
//    }
//
//    private fun initHeartRecyclerView() {
//        adapter = FoodRecyclerViewAdapter() //어댑터 객체 만듦
//        adapter.datalist = mDatas //데이터 넣어줌
//        binding.foodRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
//        binding.foodRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
//    }
//
//    private fun initializelist(/*productList : ArrayList<Product>*/) {
//
//        with(mDatas) {
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water1", "20%", "1980"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water2", "30%", "1200"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water3", "40%", "1280"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water4", "20%", "4500"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water5", "20%", "3400"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water6", "30%", "1245"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water7", "60%", "1500"))
//            add(FoodData("https://images.app.goo.gl/gfmppXq2iba9DK5v8", "water8", "20%", "1680"))
//        }
//    }
//
//    companion object {
//
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HeartFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
//    }
//}