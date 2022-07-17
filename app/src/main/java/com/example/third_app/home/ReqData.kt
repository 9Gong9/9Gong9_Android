package com.example.third_app.home

import okhttp3.MediaType
import okhttp3.RequestBody

class ReqData (userid:String){
    val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), "{\"userId\":${userid}")
}