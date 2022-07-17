package com.example.third_app.home

import android.app.Application

class ItemApplication : Application(){
    companion object{
        private var itemId : String ?= null
        public fun getItemId() : String?{
            return itemId
        }
        public fun setItemId(itemid : String){
            itemId = itemid
        }
    }
}