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
    val town: String,
    val rateMan : Int,
    val userJoinedIt: Boolean, //참여는 했는데 사람이 아직 안 차서 구매는 못함
    val userLikedIt: Boolean, //좋아요 누른 제품
    val userGotIt:Boolean, //구매까지 해서 평점 남길 수 있는 상황
    val usersRate: Double? //평점 남겼을 경우 평점 들어감, 평점 안 남겼으면 null이 들어감
)