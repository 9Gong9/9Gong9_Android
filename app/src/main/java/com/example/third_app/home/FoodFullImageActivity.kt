package com.example.third_app.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.third_app.R
import com.example.third_app.databinding.ActivityFoodFullImageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodFullImageActivity : AppCompatActivity(){
//    var userId=null
//    var userName=null
//    var foodData=null
    private lateinit var binding : ActivityFoodFullImageBinding
    var checkHeart=false
    var joinedIt=false
    var budget=UserApplication.getUserBudget()
    var itemPrice=-100
    var userGotIt=false
    var starClicked=false
    //var my_rate=-1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = ItemApplication.getItemId()
        var data : ItemFullImage ?= null
        var item : Product ?= null

        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if(itemId!=null){
            Log.e("FoodFullImage", itemId)
        }

        var userid = UserApplication.getUserId()
        //itemListService
        var foodFullImageService: FoodFullImageService = retrofit.create(FoodFullImageService::class.java)
        if (itemId != null) {
            foodFullImageService.requestItemList(itemId, userid!!).enqueue(object : Callback<ItemFullImage> {
                override fun onFailure(call: Call<ItemFullImage>, t: Throwable) {
                    Log.e("FullImage","error : itemList 호출 실패")
                }
                override fun onResponse(call: Call<ItemFullImage>, response: Response<ItemFullImage>) {
                    data = response.body()
                    Log.e("DATA!!",data.toString())
                    Log.d("FullImage", "statusCode : "+data?.statusCode.toString())
                    Log.d("FullImage", "Msg:"+data?.statusMsg.toString())
                    if(data!=null){
                        item = data?.data
                        binding.foodMainTitle.text = item?.name
                        binding.foodMainPrice.text = item?.salePrice.toString()
                        binding.foodPlace.text = item?.state + " > " + item?.area +  " > " +item?.town
                        //item 판매가
                        itemPrice= item?.salePrice!!
                        Log.e("FoodUSER!!!!",item?.userGotIt.toString())
                        userGotIt= (item?.userGotIt == true) //userGotIt true일때

                        binding.foodMainDeliverTime.text = item?.dueDate
                        //rate 받아오고 또 평점 남기기 후에는 다시 줘야함->여기는 현재 평점(내 평점 반영 전)받아오는 코드
                        binding.foodMainRate.rating = item?.rate!!.toFloat()
                        Glide.with(this@FoodFullImageActivity)
                            .load("http:"+item?.imgUrl.toString())
                            .into(binding.foodFullImg)
                        //userLikedIt 받기
                        checkHeart= (item?.userLikedIt == true)
                        if(checkHeart)binding.heartSave.setImageResource(R.drawable.redheart)
                        else binding.heartSave.setImageResource(R.drawable.emptyheart)

                        joinedIt=(item?.userJoinedIt == true)
                        if(joinedIt){binding.foodGroupJoinImg.setImageResource(R.drawable.minus)
                        binding.foodGroupJointxt.text="공구 참여 취소"}
                        else {binding.foodGroupJoinImg.setImageResource(R.drawable.plus)
                        binding.foodGroupJointxt.text="공구 참여하기"}

                        //Log.e("FoodStarErrorCatch!!:",item?.usersRate.toString())

                        starClicked=(item?.usersRate !=null)//평점 안 남겼으면 null로 오고 있는 거 맞음..?
                        if(starClicked)binding.foodCall.setImageResource(R.drawable.star_clicked)
                        else binding.foodCall.setImageResource(R.drawable.star)

                    }
    //                val intent = Intent(context, FoodFullImage::class.java)
    //                intent.putExtra("userId", mDatas[position].id)

                        }
                    })
                }
        //좋아요 클릭
        binding.heartSave.setOnClickListener {
            //좋아요 상태일 때 클릭함
            if(checkHeart){
                binding.heartSave.setImageResource(R.drawable.emptyheart)
                checkHeart=false

                if (userid != null && itemId!=null) {
                    switchNowLike(userid, itemId)
                }
            }
            //하트 빈칸일 때 클릭함
            else{
                binding.heartSave.setImageResource(R.drawable.redheart)
                checkHeart=true

                if (userid != null && itemId!=null) {
                    switchNowLike(userid, itemId)
                }

            }
        }
        //공구 클릭
        binding.foodGroupJoinImg.setOnClickListener {
            Log.e("클릭!!!!",joinedIt.toString())
            //참여중이었을 때 클릭함==공구 참여 취소
            if (joinedIt){
                budget = budget?.plus(itemPrice)
                var dialog1=AlertDialog.Builder(this)
                dialog1.setTitle("공구 신청을 취소하시겠습니까?\n 취소 시 남은 잔액: "+budget.toString()+"원")


                
                dialog1.setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                    binding.foodGroupJoinImg.setImageResource(R.drawable.plus) //참여 취소!
                    joinedIt=false
                    binding.foodGroupJointxt.text="공구 참여하기"

                    if (userid != null && itemId!=null) {
                        switchJoined(userid, itemId)
                    }
                })
                dialog1.create()
                        .show()
//                Toast.makeText(this@FoodFullImageActivity,"공구 신청을 취소하시겠습니까?\n 취소 시 남은 잔액: "+budget.toString()+"원",Toast.LENGTH_SHORT).show()
            }
            else{
                var dialog2=AlertDialog.Builder(this)
                budget = budget?.minus(itemPrice)
                dialog2.setTitle("공구 신청을 하시겠습니까?\n 신청 시 남은 잔액: "+budget.toString()+"원")


//                Toast.makeText(this@FoodFullImageActivity,"공구 신청을 하시겠습니까?\n 신청 시 남은 잔액: "+budget.toString()+"원",Toast.LENGTH_SHORT).show()
                dialog2.setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                    binding.foodGroupJoinImg.setImageResource(R.drawable.minus)
                    joinedIt=true
                    binding.foodGroupJointxt.text="공구 참여 취소"

                    if (userid != null && itemId!=null) {
                        switchJoined(userid, itemId)
                    }
                })
                dialog2.create()
                        .show()
            }
        }
        //평점 남기기 클릭
        binding.foodCall.setOnClickListener {
            //userGotIt이 false인 경우 클릭 못하게 해야함
            if(userGotIt==false){
                binding.foodCall.setImageResource(R.drawable.star)
                Toast.makeText(this,"평점은 구매 후 남길 수 있습니다.",Toast.LENGTH_SHORT).show()
            }
            else{//구매한 제품이라 평점 남기기 가능
                if(starClicked==false){
                binding.foodCall.setImageResource(R.drawable.star_clicked) //평점 남기기 클릭함!
                var dialog=AlertDialog.Builder(this) //FoodFullImageActivity로 하면 에러 나는 이유..?
                var rating=RatingBar(this)
                rating.numStars=5
//                rating.setRating(5F)
                rating.stepSize= 0.5F
//                rating.setBackgroundColor()
//                rating.width="wrap_content"
                var my_rate=0.0

                dialog.setTitle("평점을 남겨주세요!")
                    .setView(rating)
                    rating.setOnRatingBarChangeListener { ratingBar, fl, b ->
                        Log.e("여기!!!!", "진짜 여기 확인해야 해!!!")
                        my_rate= fl.toDouble()+1.0
                        Log.e("my_rate: ",my_rate.toString())}
                    dialog.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                        //Ok 버튼 선택 시 수행됨
                        Log.e("여기여기여기!!!!","여기")
                        if (userid != null && itemId!=null) {
                            giveRate(userid, itemId, my_rate.toFloat())
                        }

                    })
                dialog.create()
                dialog.show()

//                binding.foodMainRate.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
//                    binding.foodMainRate.rating=rating
//                    my_rate= rating.toDouble()
//                    Log.d("FoodFullStarRate: ",my_rate.toString())
//                }
                starClicked=true



            }
        }
        } }

        //switchNowLike  -->앤 override 안 붙여주는 게 맞는 건지
        fun switchNowLike(userid:String, itemid:String){
            //var likedItemFullImage:LikedItemFullImage?=null

            //레트로핏 객체 생성
            var retrofit=Retrofit.Builder()
                .baseUrl("http://192.249.18.195:80")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //LikedFoodFullImageService 서비스 올리기
            var likedFoodFullImageService: LikedFoodFullImageService =retrofit.create(LikedFoodFullImageService::class.java)

            var dialog=AlertDialog.Builder(this) //FoodFullImageActivity로 하면 에러 나는 이유..?
//            dialog.setTitle("관심 목록에 추가하시겠습니까?")
//            dialog.show()

            likedFoodFullImageService.requestUpdateHeartList(itemid, userid!!).enqueue(object :Callback<LikedItemFullImage>{
                override fun onFailure(call: Call<LikedItemFullImage>, t: Throwable) {
                    Log.e("FoodFullImageActivity","error : 관심 목록 PUT 실패")
                    Toast.makeText(this@FoodFullImageActivity, "관심 목록 등록을 실패했습니다.",Toast.LENGTH_SHORT).show()

//                    dialog.setMessage("관심목록 등록을 실패했습니다.")
//                    Toast.makeText(applicationContext,"error : 로그아웃 실패", Toast.LENGTH_SHORT)
                }

                override fun onResponse(
                    call: Call<LikedItemFullImage>,
                    response: Response<LikedItemFullImage>
                ) {
                    var likedItemFullImage=response.body()
                    Log.e("Liked", response.body().toString())
                    if(likedItemFullImage == null){
                        Log.e("Liked", "res 없음")
                    }

                    Log.d("Liked:", "statusCode : "+likedItemFullImage?.statusCode.toString())
                    Log.d("Liked:", "Msg : "+likedItemFullImage?.statusMsg.toString())

                    //Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
                    if(likedItemFullImage?.statusCode.toString()=="201"){ //toString 이유??
                        var likedProduct = likedItemFullImage!!.data
                        // 현재 유저 정보 저장

                        if(checkHeart)Toast.makeText(this@FoodFullImageActivity, "관심 목록 등록 완료!",Toast.LENGTH_SHORT).show()
//                            dialog.setMessage("관심 목록 등록 완료!")
                        else Toast.makeText(this@FoodFullImageActivity, "관심 목록 등록 취소!",Toast.LENGTH_SHORT).show()
                        //dialog.show()
                    }
                    else{
                        //Toast.makeText(this, "error : "+likedItemFullImage?.statusCode.toString(), Toast.LENGTH_SHORT)
                        Log.e("Liked","error: "+likedItemFullImage?.statusCode.toString())
                        dialog.setMessage(likedItemFullImage?.statusMsg.toString())
                        dialog.show()
                    }
                }
            })
            //alertDialog?.dismiss()
        }

    fun switchJoined(userid:String, itemid:String){
        var joinedItemFullImage:JoinedItemFullImage?=null

        //레트로핏 객체 생성
        var retrofit=Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //LikedFoodFullImageService 서비스 올리기
        var joinedItemFullImageService: JoinedItemFullImageService =retrofit.create(JoinedItemFullImageService::class.java)

        var dialog=AlertDialog.Builder(this) //FoodFullImageActivity로 하면 에러 나는 이유..?
//            dialog.setTitle("관심 목록에 추가하시겠습니까?")
//            dialog.show()

        joinedItemFullImageService.requestJoined(itemid, userid!!).enqueue(object :Callback<JoinedItemFullImage>{
            override fun onFailure(call: Call<JoinedItemFullImage>, t: Throwable) {
                Log.e("FoodFullImageActivity","error : 공구 참여 PUT 실패")
                dialog.setMessage("공구 참여를 실패했습니다.")
//                    Toast.makeText(applicationContext,"error : 로그아웃 실패", Toast.LENGTH_SHORT)
            }

            override fun onResponse(
                call: Call<JoinedItemFullImage>,
                response: Response<JoinedItemFullImage>
            ) {
                joinedItemFullImage=response.body()
                Log.e("Joined", response.body().toString())
                if(joinedItemFullImage == null){
                    Log.e("Joined", "res 없음")
                }

                Log.d("Joined:", "statusCode : "+joinedItemFullImage?.statusCode.toString())
                Log.d("Joined:", "Msg : "+joinedItemFullImage?.statusMsg.toString())

                //Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
                if(joinedItemFullImage?.statusCode.toString()=="201"){ //toString 이유??
                    var joinedProduct = joinedItemFullImage!!.data //nowJoined 써도 됐겠다


                    if(joinedIt)Toast.makeText(this@FoodFullImageActivity,"공구 참여 등록 완료!",Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this@FoodFullImageActivity,"공구 참여 취소 완료!",Toast.LENGTH_SHORT).show()
                }
                else{
                    //Toast.makeText(this, "error : "+likedItemFullImage?.statusCode.toString(), Toast.LENGTH_SHORT)
                    Log.e("Joined","error: "+joinedItemFullImage?.statusCode.toString())
                    dialog.setMessage(joinedItemFullImage?.statusMsg.toString())
                    dialog.show()
                }
            }
        })
        //alertDialog?.dismiss()
    }

    fun giveRate(userid:String, itemid:String, my_rate:Float){
        var giveStarFullImage:GiveStarFullImage?=null

        //레트로핏 객체 생성
        var retrofit=Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //LikedFoodFullImageService 서비스 올리기
        var giveStarFullImageService: GiveStarFullImageService =retrofit.create(GiveStarFullImageService::class.java)

        var dialog=AlertDialog.Builder(this) //FoodFullImageActivity로 하면 에러 나는 이유..?
//            dialog.setTitle("관심 목록에 추가하시겠습니까?")
//            dialog.show()

        giveStarFullImageService.requestGiveStar(itemid, userid!!,my_rate).enqueue(object :Callback<GiveStarFullImage>{
            override fun onFailure(call: Call<GiveStarFullImage>, t: Throwable) {
                Log.e("FoodFullImageActivity","error : 퍙점 남기기 PUT 실패")
                dialog.setMessage("평점 남기기를를 실패했니다.")
//                    Toast.makeText(applicationContext,"error : 로그아웃 실패", Toast.LENGTH_SHORT)
            }

            @SuppressLint("LongLogTag")
            override fun onResponse(
                call: Call<GiveStarFullImage>,
                response: Response<GiveStarFullImage>
            ) {
                giveStarFullImage=response.body()
                Log.e("Stared: ", response.body().toString())
                if(giveStarFullImage == null){
                    Log.e("Stared: ", "res 없음")
                }

                Log.d("Stared:", "statusCode : "+giveStarFullImage?.statusCode.toString())
                Log.d("Stared:", "Msg : "+giveStarFullImage?.statusMsg.toString())

                //Toast.makeText(applicationContext, login?.statusMsg.toString(), Toast.LENGTH_SHORT)
                if(giveStarFullImage?.statusCode.toString()=="201"){ //toString 이유??
                    var newAverageRate = giveStarFullImage!!.data?.newAvgRate
                    Log.e("newAvgRate", newAverageRate.toString())
                    if (newAverageRate != null) {
                        binding.foodMainRate.rating= newAverageRate.toFloat()
                    } //업데이트 시켜주기                    Log.e("newAvgRate", newAverageRate.toString())
                    Log.e("binding.foodMainRate.rating", binding.foodMainRate.rating.toString())

                    if(starClicked)Toast.makeText(this@FoodFullImageActivity, "평점 남기기 완료!",Toast.LENGTH_SHORT).show()
                }
                else{
                    //Toast.makeText(this, "error : "+likedItemFullImage?.statusCode.toString(), Toast.LENGTH_SHORT)
                    Log.e("Stared: ","error: "+giveStarFullImage?.statusCode.toString())
                    dialog.setMessage(giveStarFullImage?.statusMsg.toString())
                    dialog.show()
                }
            }
        })
        //alertDialog?.dismiss()
    }
        //val foodData= intent.getSerializableExtra("foodData") as FoodData
    }
    //class 에 , HomeFragment.OnDataPassListener 추가해야 함!!!
//    override fun onUserIDPass(userId: String?) {
//        System.out.println("FoodFullImage: foodDataName: $userId")
//    }
//
//    override fun onUserNamePass(userName: String?) {
//        System.out.println("FoodFullImage: foodDataName: $userName")
//    }
//    override fun OnDataPass(data: FoodData) {
//        System.out.println("FoodFullImage: foodDataName:  "+data.food_name)
//    } }