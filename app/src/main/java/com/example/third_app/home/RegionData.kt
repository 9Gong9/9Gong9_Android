package com.example.third_app.home

data class RegionData(
    val `data`: City,
    val statusCode: Int,
    val statusMsg: String
)

data class City(
    val 대전광역시: DajeonGu
)


data class DajeonGu(
    val 대덕구: List<String>,
    val 동구: List<String>,
    val 서구: List<String>,
    val 유성구: List<String>,
    val 중구: List<String>
)