package com.example.third_app.home

import android.app.Application

class UserApplication : Application(){
    companion object{
        private var userId : String ?= null
        private var userBudget:Int?=null   //여기서 userBudget가 어케 업데이트 되는 거임>?
        public fun getUserId() : String?{
            return userId
        }
        public fun setUserId(userid : String){
            userId = userid
        }
        public fun deleteUserId(){
            userId = null
        }
        //user budget 받기
        public fun getUserBudget():Int?{
            return userBudget
        }
        public fun setUserBudget(budget: Int?){
            userBudget=budget
        }
    }
}