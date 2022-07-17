package com.example.third_app.home

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.third_app.R
import com.example.third_app.databinding.ActivityFoodFullImageBinding
import com.example.third_app.login.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodFullImageActivity : AppCompatActivity(){
//    var userId=null
//    var userName=null
//    var foodData=null
    private lateinit var binding : ActivityFoodFullImageBinding
    var checkHeart=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = ItemApplication.getItemId()
        var data : ItemFullImage ?= null
        var item : Product ?= null

        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if(itemId!=null){
            Log.e("FoodFullImage", itemId)
        }

        var userid = UserApplication.getUserId()
        //itemListService
        var foodFullImageService: FoodFullImageService = retrofit.create(FoodFullImageService::class.java)
        if (itemId != null) {
            foodFullImageService.requestItemList(itemId, userid!!).enqueue(object : Callback<ItemFullImage> {
                override fun onFailure(call: Call<ItemFullImage>, t: Throwable) {
                    Log.e("FullImage","error : itemList 호출 실패")
                }
                override fun onResponse(call: Call<ItemFullImage>, response: Response<ItemFullImage>) {
                    data = response.body()
                    Log.d("FullImage", "statusCode : "+data?.statusCode.toString())
                    Log.d("FullImage", "Msg:"+data?.statusMsg.toString())
                    if(data!=null){
                        item = data?.data
                        binding.foodMainTitle.text = item?.name
                        binding.foodMainPrice.text = item?.salePrice.toString()
                        binding.foodMainDeliverTime.text = item?.dueDate
                        binding.foodMainRate.text = item?.rate
                        Glide.with(this@FoodFullImageActivity)
                            .load("http:"+item?.imgUrl.toString())
                            .into(binding.foodFullImg)
                    }
    //                val intent = Intent(context, FoodFullImage::class.java)
    //                intent.putExtra("userId", mDatas[position].id)

                        }
                    })
                }
//        binding.heartSave.setOnClickListener {
//            //좋아요 상태일 때 클릭함
//            if(checkHeart){
//                binding.heartSave.setImageResource(R.drawable.emptyheart)
//                checkHeart=false
//
//                if (userid != null && itemId!=null) {
//                    switchNowLike(userid, itemId)
//                }
//            }
//            //하트 빈칸일 때 클릭함
//            else{
//                binding.heartSave.setImageResource(R.drawable.redheart)
//                checkHeart=true
//
//                if (userid != null && itemId!=null) {
//                    switchNowLike(userid, itemId)
//                }
//
//            }
//        }
//
//        }
//
//        //switchNowLike  -->앤 override 안 붙여주는 게 맞는 건지
//        fun switchNowLike(userid:String, itemid:String){
//            var likedItemFullImage:LikedItemFullImage?=null
//
//            //레트로핏 객체 생성
//            var retrofit=Retrofit.Builder()
//                .baseUrl("http://192.249.18.195:80")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            //LikedFoodFullImageService 서비스 올리기
//            var likedFoodFullImageService: LikedFoodFullImageService =retrofit.create(LikedFoodFullImageService::class.java)
//
//            var dialog=AlertDialog.Builder(this) //FoodFullImageActivity로 하면 에러 나는 이유..?
//            dialog.setTitle("관심 목록에 추가하시겠습니까?")
//
//            likedFoodFullImageService.requestUpdateHeartList(itemid, userid!!).enqueue(object :Callback<LikedItemFullImage>{
//                override fun onFailure(call: Call<LikedItemFullImage>, t: Throwable) {
//                    Log.e("FoodFullImageActivity","error : 관심 목록 PUT 실패")
//                    dialog.setMessage("관심목록 등록을 실패했습니다.")
////                    Toast.makeText(applicationContext,"error : 로그아웃 실패", Toast.LENGTH_SHORT)
//                }
//
//                override fun onResponse(
//                    call: Call<LikedItemFullImage>,
//                    response: Response<LikedItemFullImage>
//                ) {
//                    likedItemFullImage=response.body()
//                    Log.e("Liked", response.body().toString())
//                    if(likedItemFullImage == null){
//                        Log.e("Liked", "res 없음")
//                    }
//
//                    Log.d("Liked:", "statusCode : "+likedItemFullImage?.statusCode.toString())
//                    Log.d("Liked:", "Msg : "+likedItemFullImage?.statusMsg.toString())
//
//                    //Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
//                    if(likedItemFullImage?.statusCode.toString()=="201"){ //toString 이유??
//                        var likedProduct = likedItemFullImage!!.data
//                        // 현재 유저 정보 저장
//
//                        dialog.setMessage("관심 목록 등록 완료!")
//                    }
//                    else{
//                        //Toast.makeText(this, "error : "+likedItemFullImage?.statusCode.toString(), Toast.LENGTH_SHORT)
//                        Log.e("Liked","error: "+likedItemFullImage?.statusCode.toString())
//                        dialog.setMessage(likedItemFullImage?.statusMsg.toString())
//                        dialog.show()
//                    }
//                }
//            })
            //alertDialog?.dismiss()
        }

        //val foodData= intent.getSerializableExtra("foodData") as FoodData
    }


    //class 에 , HomeFragment.OnDataPassListener 추가해야 함!!!
//    override fun onUserIDPass(userId: String?) {
//        System.out.println("FoodFullImage: foodDataName: $userId")
//    }
//
//    override fun onUserNamePass(userName: String?) {
//        System.out.println("FoodFullImage: foodDataName: $userName")
//    }
//    override fun OnDataPass(data: FoodData) {
//        System.out.println("FoodFullImage: foodDataName:  "+data.food_name)
//    } }