package com.example.third_app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.third_app.databinding.HomeListBinding
import android.view.View

class FoodRecyclerViewAdapter() : RecyclerView.Adapter<FoodRecyclerViewAdapter.MyViewHolder>() {
    //변수 하드코딩 바꿔야 함*******************************
    var userId="7"

    var datalist = mutableListOf<Product>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가

    inner class MyViewHolder(private val binding: HomeListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(foodData: Product){
            //binding.dogPhotoImg.=dogData.dog_img

            //val imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            val imageView = binding.homeFoodImg
//            val url = "https://cdn.pixabay.com/photo/2021/08/03/07/03/orange-6518675_960_720.jpg"

            Glide.with(this.itemView)
                .load("http:"+foodData.imgUrl) // 불러올 이미지 url
                .into(imageView) // 이미지를 넣을 뷰

//                .circleCrop() // 동그랗게 자르기
//            //binding.homeFoodImg.setImageURI(foodData.food_img.toString())
//            binding.homeFoodContext.text= foodData.food_name.toString()
//            binding.homeFoodPercent.text=foodData.food_percent.toString()
//            binding.homeFoodPrice.text=foodData.food_price.toString()

            binding.homeFoodContext.text= foodData.name
            binding.homeFoodPercent.text= foodData.rate
            binding.homeFoodPrice.text= foodData.salePrice.toString()

//            itemView.setOnClickListener {
//                Intent(context, FoodFullImage::class.java).apply{
//                    putExtra("userId",userId)
//                    putExtra("FoodData",foodData)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run{context.startActivity(this)}
//            }

            //FoodRecyclerViewAdapter에 ClickListener Interface 만들어주기
//            val pos = adapterPosition
//            if(pos!= RecyclerView.NO_POSITION)
//            {
//                itemView.setOnClickListener {
//                    listener?.onItemClick(itemView,foodData,pos)
//                }
//            }
        }
    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=HomeListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])

//        //recyclerview clickListener
//        holder.itemView.setOnClickListener {
//            ItemClickListener.OnClick(it, position)
//        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    //FoodRecyclerViewAdapter에 clickListener Interface 만들어주기
//    interface OnItemClickListener{
//        fun onItemClick(v: View, data: FoodData, pos : Int)
//    }
//    private var listener : OnItemClickListener? = null
//    fun setOnItemClickListener(listener : OnItemClickListener) {
//        this.listener = listener
//    }
//    //클릭 인터페이스
//    interface ItemClickListener{
//        fun OnClick(view: View, position: Int)
//    }
//    //클릭 리스너
//    private lateinit var itemClickListener: ItemClickListener
//    fun setItemClickListener(itemClickListener: ItemClickListener){
//        this.itemClickListener=itemClickListener
//    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener=onItemClickListener
    }
    private lateinit var itemClickListener: OnItemClickListener

}
