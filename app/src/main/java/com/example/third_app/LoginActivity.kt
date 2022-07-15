package com.example.third_app

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.third_app.databinding.ActivityLoginBinding
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {
    var login:Login? = null
    private var mBinding: ActivityLoginBinding ?= null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)

        binding.btnKakaoLogin.setOnClickListener{
            // 카카오계정으로 로그인
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "로그인 실패", error)
                }
                else if (token != null) {
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    finish()
                }
            }
        }

        // 레트로핏 객체 생성.
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 로그인
        binding.btnLoginLogin.setOnClickListener{
            //로그인 서비스 올리기
            var loginService: LoginService = retrofit.create(LoginService::class.java)
            var id = binding.etId.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()
            loginService.requestLogin(id, password).enqueue(object: Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("Login", "error : 로그인 실패")
                    var dialog = AlertDialog.Builder(this@LoginActivity)
                    dialog.setTitle("Error")
                    dialog.setMessage("로그인 호출을 실패했습니다.")
                    dialog.show()
                    //Toast.makeText(applicationContext,"error : 로그인 실패", Toast.LENGTH_SHORT)
                }
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    login = response.body()
                    Log.d("Login:", "statusCode : "+login?.statusCode.toString())
                    Log.d("Login:", "Msg : "+login?.statusMsg.toString())

                    Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
                    if(login?.statusCode.toString()=="201"){
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        var dialog = AlertDialog.Builder(this@LoginActivity)
                        dialog.setTitle("Error "+login?.statusCode.toString())
                        dialog.setMessage(login?.statusMsg.toString())
                        dialog.show()
                    }
                }
            })
        }

        // 회원가입
        binding.btnSignUp.setOnClickListener{
            //Toast.makeText(this, "토스트 메시지", Toast.LENGTH_SHORT)
            val intent = Intent(this, SignUpActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}