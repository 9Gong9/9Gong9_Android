package com.example.third_app.home

import android.util.Log

class RegionApplication {
    companion object{
        private var region : String = "대전광역시/유성구/신성동"
        private var city : String = "대전광역시"
        private var gu : String = "유성구"
        private var dong : String = "신성동"

        val region_list = arrayOf("봉명동", "구암동", "원신흥동", "죽동", "궁동", "어은동", "신성동", "도룡동", "관평동", "구룡동")

        private var dong_list = mutableListOf<String>()
        fun setDong(dong_id : Int){
            dong = region_list[dong_id]
            Log.e("동".toString(), dong)
        }
        fun getDong():String?{
            return dong
        }
        fun setGu(gu_name : String){
            gu = gu_name
        }
        fun getGu():String?{
            return gu
        }
        fun getRegion() : String {
            region = city+"/"+gu +"/"+ dong
            return region
        }
        fun setDongList(gu_index: Int) {
           // dong_list = region_list[gu_index].toMutableList()
        }
        fun getDongList(): MutableList<String> {
            return dong_list
        }
    }
}
