package com.example.third_app.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.third_app.MainActivity
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
    private lateinit var guAdapter : GuRecyclerAdapter
    private lateinit var dongAdapter : DongRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gu_datalist = mutableListOf<String>()
        var dong_datalist = mutableListOf<String>()
        var regionData : RegionData?

        var region_datalist:DajeonGu? = null

        var gu_index : Int = 0


        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 지역구 리스트
        val gu_list = mutableListOf("유성구")
        //val gu_list = mutableListOf("서구", "유성구", "대덕구", "동구", "중구")
        val region_list = arrayOf(
            arrayOf("용문동", "탄방동", "가장동", "갈마동", "둔산동", "월평동", "가수원동", "도안동", "관저동", "흑석동", "만년동"),
            arrayOf("봉명동", "구암동", "원신흥동", "죽동", "궁동", "어은동", "신성동", "도룡동", "관평동", "구룡동") ,
            arrayOf("오정동","송촌동","신탄진동"),
            arrayOf("용계동","신흥동","판암동","신안동"),
            arrayOf("은행동","목동","대흥동", "용두동","태평동")
        )

        guAdapter = GuRecyclerAdapter()
        guAdapter.datalist = gu_list
        binding.guRecyclerView.layoutManager = LinearLayoutManager(this@AddressSearchActivity)

        dongAdapter = DongRecyclerAdapter()
        dongAdapter.datalist = region_list[1].toMutableList()
        guAdapter.notifyDataSetChanged()
        dongAdapter.notifyDataSetChanged()
        guAdapter.setItemClickListener(object : GuRecyclerAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Log.e("되고있냐??", "ㅇㅇ")
                dong_datalist = region_list[guAdapter.getSelectPos()].toMutableList()
                dongAdapter.datalist = dong_datalist
//                binding.dongRecyclerView.layoutManager = LinearLayoutManager(this@AddressSearchActivity)
            }
        })
        binding.guRecyclerView.adapter = guAdapter //리사이클러뷰에 어댑터 연결
        binding.dongRecyclerView.layoutManager = LinearLayoutManager(this@AddressSearchActivity)
        binding.dongRecyclerView.adapter = dongAdapter //리사이클러뷰에 어댑터 연결

        binding.btnSubmit.setOnClickListener{
            Log.e("address", RegionApplication.getRegion())
            RegionApplication.regionInitialized()
            val intent = Intent(this@AddressSearchActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener{
            RegionApplication.isInitialized()
            Log.e("address", RegionApplication.getRegion())
            val intent = Intent(this@AddressSearchActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}