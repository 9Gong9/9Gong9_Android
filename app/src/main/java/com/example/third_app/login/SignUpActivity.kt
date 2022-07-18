package com.example.third_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.third_app.databinding.ActivitySignUpBinding
import com.example.third_app.login.Login
import com.example.third_app.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class SignUpActivity : AppCompatActivity() {
    var res: Login? = null // SignUp POST할 경우 회신 데이터 양식
    private var mBinding: ActivitySignUpBinding?= null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 레트로핏 객체 생성.
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        //intent 초기화
        val intent = Intent(this, LoginActivity::class.java)
        
        binding.btnJoin.setOnClickListener{
            var signUpService: SignUpService = retrofit.create(SignUpService::class.java)
            var id = binding.etId.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()
            var name = binding.etName.text.toString().trim()
            signUpService.requestSignUp(id, password, name).enqueue(object: Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@SignUpActivity)
                    Log.e("SignUp:", "error : 회원가입 호출 실패")
                    dialog.setTitle("Error")
                    dialog.setMessage("회원가입 호출을 실패했습니다.")
                    dialog.show()
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    res = response.body()
                    Log.d("SignUp:", "statusCode : "+res?.statusCode.toString())
                    Log.d("SignUp:", "Msg : "+res?.statusMsg.toString())
                    if(res?.statusCode.toString()=="201"){
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        var dialog = AlertDialog.Builder(this@SignUpActivity)
                        dialog.setTitle("Error "+res?.statusCode.toString())
                        dialog.setMessage(res?.statusMsg.toString())
                        dialog.show()
                    }
                }

            })
        
        }
        binding.btnBack.setOnClickListener{
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }
}

interface SignUpService {
    @FormUrlEncoded //인코딩 옵션
    @POST("/user") //어떤 형태로 데이터를 전송할 것인가
    fun requestSignUp(
        @Field("id") id:String,
        @Field("password") password:String,
        @Field("name") name:String
    ) : Call<Login> // 어떤 형태로 데이터를 받을 것인가
}