package com.example.third_app.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.third_app.home.FoodRecyclerViewAdapter
import com.example.third_app.home.Product
import com.example.third_app.databinding.ProductListBinding

class PreviousItemListAdapter() : RecyclerView.Adapter<PreviousItemListAdapter.MyViewHolder>() {
    var datalist = mutableListOf<Product>()
    inner class MyViewHolder(private val binding : ProductListBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("RestrictedApi")
        fun bind(productData : Product){
            if(productData != null){
                val imageUrl = productData.imgUrl
                val imageView = binding.productImg
                Glide.with(this.itemView)
                    .load(imageUrl) // 불러올 이미지 url
                    .into(imageView) // 이미지를 넣을 뷰
                binding.homeFoodContext.text= productData.name
                binding.homeFoodPercent.text=productData.rate
                binding.homeFoodPrice.text=productData.salePrice.toString()
                //Preload 이미지 로딩 속도 빠르게

//                var position = adapterPosition
//                if (position <= datalist.size) {
//                    val endPosition = if (position + 6 > datalist.size) {
//                        datalist.size
//                    } else {
//                        position + 6
//                    }
//                    datalist.subList(position, endPosition ).map { imageUrl }.forEach {
//                        //preload(this.itemView, binding)
//                        preload()
//                    }
//                }
            }


        }

    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= ProductListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: FoodRecyclerViewAdapter.OnItemClickListener){
        this.itemClickListener=onItemClickListener
    }
    private lateinit var itemClickListener: FoodRecyclerViewAdapter.OnItemClickListener

}