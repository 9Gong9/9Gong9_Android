package com.example.third_app.category
import android.app.Application

class CategoryApplication : Application() {
    companion object{
        private var categoryId : String = ""
        private var categoryRealId : String = "전체"
        public fun getCategoryId() : String?{
            return categoryId
        }
        public fun setCategoryId(categoryid : String){
            categoryId = categoryid
        }
        public fun deleteCategoryId(){
            categoryId = ""
        }
        fun setCategoryRealId(categoryrealid : String){
            categoryRealId = categoryrealid
        }
        fun getCategoryRealId() : String{
            return categoryRealId
        }
        public fun deleteCategoryRealId(){
            categoryId = "전체"
        }
    }
}
