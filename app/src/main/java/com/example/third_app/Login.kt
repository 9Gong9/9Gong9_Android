package com.example.third_app

data class Login( //OUTPUT 서비스
    val `data`: LoginData,
    val statusCode: Int,
    val statusMsg: String
)