package com.example.third_app

data class LoginData(
    val id: String,
    val isActive: Boolean?,
    val name: String?,
    val password: String?
) //일부 데이터만 받으면 되는 건가?