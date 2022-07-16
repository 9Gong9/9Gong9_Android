package com.example.third_app

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.third_app.databinding.FragmentUserBinding
import com.kakao.sdk.talk.TalkApiClient
import java.util.Collections.list

class UserFragment : Fragment() {
    private var mBinding : FragmentUserBinding ?= null
    private val binding get() = mBinding!!
    private lateinit var adapter : ItemListAdapter

    // product List
    val mDatas=mutableListOf<Product>()

    // Context를 액티비티로 형변환해서 할당
    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    // sharedManager 선언
    private val sharedManager : SharedManager by lazy { SharedManager(mainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentUserBinding.inflate(inflater, container, false)

        //리스트 초기화
        this.initializelist()
        //리사이클러뷰 바인딩
        initItemListRecyclerView()

        var userName = binding.tvUserName
        var profileImg = binding.ivUser
        // 카카오톡 프로필 가져오기
        TalkApiClient.instance.profile { profile, error ->
            if (error != null) {
                val currentUser = sharedManager.getCurrentUser()
                if(currentUser != null){
                   userName.text = currentUser.name
                }
                else{
                    Log.e(TAG, "카카오톡 프로필 가져오기 실패", error)
                }
            } else if (profile != null) {
                Log.i(
                    TAG, "카카오톡 프로필 가져오기 성공" +
                            "\n닉네임: ${profile.nickname}" +
                            "\n프로필사진: ${profile.thumbnailUrl}"
                )
                userName.text = profile.nickname
                Glide.with(this).load(profile.thumbnailUrl).circleCrop().into(profileImg)
            }
        }

        return binding.root
    }
    
    private fun initItemListRecyclerView(){
        adapter = ItemListAdapter()
        adapter.datalist = mDatas
        binding.ItemListRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.ItemListRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }
    private fun initializelist() {
        with(mDatas){
            add(Product("https://images.app.goo.gl/gfmppXq2iba9DK5v8","", 10,"","","","",10000,"0",8000,"",""))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}