package com.example.third_app
import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // kakao sdk 초기화
        KakaoSdk.init(this, getString(R.string.kakao_application_key))
    }
}