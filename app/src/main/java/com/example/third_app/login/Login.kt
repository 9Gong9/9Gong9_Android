package com.example.third_app.login

data class Login( //OUTPUT 서비스
    val `data`: LoginData,
    val statusCode: Int,
    val statusMsg: String
)