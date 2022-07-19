package com.example.third_app.category
import android.app.Application

class CategoryApplication : Application() {
    companion object{
        private var categoryId : String = ""
        public fun getCategoryId() : String?{
            return categoryId
        }
        public fun setCategoryId(categoryid : String){
            categoryId = categoryid
        }
        public fun deleteCategoryId(){
            categoryId = ""
        }
    }
}
