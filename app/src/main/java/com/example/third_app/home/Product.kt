package com.example.third_app.home

data class Product(
    val area: String,
    val category: String,
    val dueDate: String,
    val id: Int,
    val imgUrl: String,
    val joiners: List<Any>,
    val likes: List<Any>,
    val minMan: Int,
    val name: String,
    val nowMan: Int,
    val orgPrice: Int,
    val rate: String,
    val salePrice: Int,
    val state: String,
    val town: String
)