package com.example.third_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.Serializable
class FoodFullImage : AppCompatActivity(){
//    var userId=null
//    var userName=null
//    var foodData=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_full_image)

        val userId = intent.getStringExtra("userId")
        val userName = intent.getStringExtra("userName")
        System.out.println("FoodFullImage!!!!!"+userId)
        System.out.println("FoodFullImage!!!!!"+userName)

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
//    }
}