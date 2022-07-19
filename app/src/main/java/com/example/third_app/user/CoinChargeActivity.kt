package com.example.third_app.user

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.third_app.MainActivity
import com.example.third_app.R
import com.example.third_app.databinding.ActivityCoinChargeBinding
import com.example.third_app.databinding.ActivityFoodFullImageBinding
import com.example.third_app.home.FoodFullImageActivity
import com.example.third_app.home.LikedFoodFullImageService
import com.example.third_app.home.LikedItemFullImage
import com.example.third_app.home.UserApplication
import com.example.third_app.login.Login
import com.kakao.sdk.user.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinChargeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCoinChargeBinding
    var charge="0"
    var userId=UserApplication.getUserId().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_coin_charge)
        binding=ActivityCoinChargeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.e("코인!!!!!!!!!!",charge.toString())
        binding.currentBudget.text= UserApplication.getUserBudget().toString()
        //binding.afterPayment.text=(UserApplication.getUserBudget()?.plus(charge.toInt())).toString()
        //레트로핏 객체 생성

        binding.payBtn.setOnClickListener {
            charge= binding.addedCharge.text.toString()
            var retrofit= Retrofit.Builder()
                .baseUrl("http://192.249.18.195:80")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //LikedFoodFullImageService 서비스 올리기
            var coinChargeService: CoinChargeService =retrofit.create(
                CoinChargeService::class.java)

            var dialog= AlertDialog.Builder(this) //FoodFullImageActivity로 하면 에러 나는 이유..?
//            dialog.setTitle("관심 목록에 추가하시겠습니까?")
//            dialog.show()

            Log.e("charge: ", charge)
            coinChargeService.requestCoin(userId, charge.toInt()).enqueue(object :
                Callback<CoinCharge> {
                override fun onFailure(call: Call<CoinCharge>, t: Throwable) {
                    Log.e("CoinChargeActivity","error : 충전 PUT 실패")
                    Toast.makeText(this@CoinChargeActivity, "충전을 실패했습니다.", Toast.LENGTH_SHORT).show()

//                    dialog.setMessage("관심목록 등록을 실패했습니다.")
//                    Toast.makeText(applicationContext,"error : 로그아웃 실패", Toast.LENGTH_SHORT)
                }

                override fun onResponse(
                    call: Call<CoinCharge>,
                    response: Response<CoinCharge>
                ) {//likedItemFullImage
                    var coinChargeIng=response.body()
                    Log.e("CoinCharge", response.body().toString())
                    if(coinChargeIng == null){
                        Log.e("CoinCharge", "res 없음")
                    }

                    Log.d("CoinCharge:", "statusCode : "+coinChargeIng?.statusCode.toString())
                    Log.d("CoinCharge:", "Msg : "+coinChargeIng?.statusMsg.toString())

                    //Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
                    if(coinChargeIng?.statusCode.toString()=="204"){ //toString 이유??
                        var coinProduct = coinChargeIng!!.data
                        // 현재 유저 정보 저장
                        UserApplication.setUserBudget(charge.toInt())
                        Toast.makeText(this@CoinChargeActivity, "충전 완료!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        //Toast.makeText(this, "error : "+likedItemFullImage?.statusCode.toString(), Toast.LENGTH_SHORT)
                        Log.e("CoinCharge","error: "+coinChargeIng?.statusCode.toString())
                        dialog.setMessage(coinChargeIng?.statusMsg.toString())
                        dialog.show()
                    }
                }
            })

            val intent= Intent(this@CoinChargeActivity, MainActivity::class.java)
            startActivity(intent)

        }

        binding.goBack.setOnClickListener {
            val intent= Intent(this@CoinChargeActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}