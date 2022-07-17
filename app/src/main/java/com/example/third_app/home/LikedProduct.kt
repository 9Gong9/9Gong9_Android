package com.example.third_app.home

data class LikedProduct ( //JSON 형식에서 모두 string인데 이렇게 명시해주면 해당 형식으로 들어오나?
    val userId: String, //포함 안시켜야 하나?
    val id: Int,
    val nowLiked: Boolean
        )
