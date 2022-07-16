package com.example.third_app

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
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
    private val sharedManager : SharedManager by lazy { SharedManager(this) }
    var alertDialog : AlertDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)
//        var dialog = AlertDialog.Builder(this@LoginActivity)
//        alertDialog = dialog.create()

        // 레트로핏 객체 생성.
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //로그인 서비스 올리기
        var loginService: LoginService = retrofit.create(LoginService::class.java)

        // 카카오계정으로 로그인
        binding.btnKakaoLogin.setOnClickListener{
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "로그인 실패", error)
                }
                else if (token != null) {
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.me{ user, error ->
                        if (error != null){
                            Log.e(TAG, "사용자 정보 요청 실패", error)
                        }
                        else if(user!=null){
                            Log.d(TAG, "사용자 정보 요청 성공" +
                                    "\n회원번호: ${user.id}" +
                                    "\n이메일: ${user.kakaoAccount?.email}" +
                                    "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                    "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                            loginService.requestLogin(user.id.toString(), "", user.kakaoAccount?.profile?.nickname.toString(), true).enqueue(object: Callback<Login> {
                                override fun onFailure(call: Call<Login>, t: Throwable) {
                                    Log.e("Login", "error : 로그인 호출 실패")
                                    Toast.makeText(this@LoginActivity, "error : 로그인 호출 실패", Toast.LENGTH_SHORT)
//                                    dialog.setTitle("Error")
//                                    dialog.setMessage("로그인 호출을 실패했습니다.")
//                                    dialog.show()
//                                    alertDialog?.dismiss()
                                }
                                override fun onResponse(
                                    call: Call<Login>,
                                    response: Response<Login>
                                ) {
                                    login = response.body()
                                    Log.d("Login:", "statusCode : "+login?.statusCode.toString())
                                    Log.d("Login:", "Msg : "+login?.statusMsg.toString())

                                    //Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
                                    if(login?.statusCode.toString()=="201"){
                                        var loginData = login!!.data
                                        // 현재 유저 정보 저장
                                        val currentUser = LoginData(
                                            id = loginData.id,
                                            isActive = loginData.isActive,
                                            name = loginData.name,
                                            password = loginData.password
                                        )
                                        sharedManager.saveCurrentUser(currentUser)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else{
//                                        dialog.setTitle("Error "+login?.statusCode.toString())
                                        Toast.makeText(this@LoginActivity, "error : "+login?.statusCode.toString(), Toast.LENGTH_SHORT)
//                                        dialog.setMessage(login?.statusMsg.toString())
//                                        dialog.show()
//                                        alertDialog?.dismiss()
                                    }

                                }
                            })
                        }
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    finish()
                }
            }
        }


        // 로그인
        binding.btnLoginLogin.setOnClickListener{

            var id = binding.etId.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()
            loginService.requestLogin(id, password, "", false).enqueue(object: Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("Login", "error : 로그인 호출 실패")
//                    dialog.setTitle("Error")
//                    dialog.setMessage("로그인 호출을 실패했습니다.")
//                    dialog.show()
//                    alertDialog?.dismiss()
                    Toast.makeText(applicationContext,"error : 로그인 실패", Toast.LENGTH_SHORT)
                }
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    login = response.body()
                    Log.d("Login:", "statusCode : "+login?.statusCode.toString())
                    Log.d("Login:", "Msg : "+login?.statusMsg.toString())

                    if(login?.statusCode.toString()=="201"){
                        var loginData = login!!.data
                        // 현재 유저 정보 저장
                        val currentUser = LoginData(
                            id = loginData.id,
                            isActive = loginData.isActive,
                            name = loginData.name,
                            password = loginData.password
                        )
                        sharedManager.saveCurrentUser(currentUser)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        finish()
                    }
                    else{
//                        dialog.setTitle("Error "+login?.statusCode.toString())
//                        dialog.setMessage(login?.statusMsg.toString())
//                        dialog.show()
//                        alertDialog?.dismiss()
                          Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
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
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.'
        mBinding = null
        super.onDestroy()
    }
}