package com.example.third_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.third_app.databinding.HomeListBinding
import com.example.third_app.databinding.ProductListBinding

class ItemListAdapter() : RecyclerView.Adapter<ItemListAdapter.MyViewHolder>() {
    var datalist = mutableListOf<Product>()
    inner class MyViewHolder(private val binding : ProductListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(productData : Product){
            val imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            val imageView = binding.productImg
            Glide.with(this.itemView)
                .load(imageUrl) // 불러올 이미지 url
                .into(imageView) // 이미지를 넣을 뷰
            binding.homeFoodContext.text= productData.name.toString()
            binding.homeFoodPercent.text=productData.rate.toString()
            binding.homeFoodPrice.text=productData.salePrice.toString()
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListAdapter.MyViewHolder {
        val binding= ProductListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: ItemListAdapter.MyViewHolder, position: Int) {
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