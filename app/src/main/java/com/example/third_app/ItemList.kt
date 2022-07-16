package com.example.third_app

data class ItemList(
    val `data`: List<Product>,
    val statusCode: Int,
    val statusMsg: String
)