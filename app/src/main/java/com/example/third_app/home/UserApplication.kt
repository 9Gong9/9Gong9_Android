package com.example.third_app.home

import android.app.Application

class UserApplication : Application(){
    companion object{
        private var userId : String ?= null
        public fun getUserId() : String?{
            return userId
        }
        public fun setUserId(userid : String){
            userId = userid
        }
        public fun deleteUserId(){
            userId = null
        }
    }
}