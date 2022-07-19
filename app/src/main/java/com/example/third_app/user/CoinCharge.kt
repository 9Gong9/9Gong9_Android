package com.example.third_app.user

import com.example.third_app.home.LikedProduct

data class CoinCharge (
    val `data`: CoinProduct,
    val statusCode: Int,
    val statusMsg: String
        )