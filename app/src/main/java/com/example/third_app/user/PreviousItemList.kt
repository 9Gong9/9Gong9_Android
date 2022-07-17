package com.example.third_app.user

import com.example.third_app.home.Product

data class PreviousItemList(
    val `data`: List<Product>,
    val statusCode: Int,
    val statusMsg: String
)
