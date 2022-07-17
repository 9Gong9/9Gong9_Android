package com.example.third_app.login
import android.app.Application
import com.example.third_app.R
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // kakao sdk 초기화
        KakaoSdk.init(this, getString(R.string.kakao_application_key))
    }
}
