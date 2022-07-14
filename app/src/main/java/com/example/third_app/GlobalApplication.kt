package com.example.third_app
import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // kakao sdk 초기화
        KakaoSdk.init(this, "0e5adec60ff5a89f23d916917c8eb7d1")
    }
}