//package com.example.third_app.home
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.third_app.databinding.HomeListBinding
//import com.example.third_app.databinding.RegionListBinding
//
//class GuRecyclerAdapter() : RecyclerView.Adapter<GuRecyclerAdapter.MyViewHolder>() {
//    var datalist = mutableListOf<String>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
//    var row_index : Int ?= null
//    inner class MyViewHolder(private val binding: RegionListBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(name: String){
//            binding.tvRegion.text = name
//            itemView.setOnClickListener {
//                itemClickListener.onClick(it,adapterPosition)
//                row_index = adapterPosition
//                notifyDataSetChanged()
//            }
//        }
//        if(row_index == adapterPosition){
//            binding.
//        }
//        else{}
//    }
//
//    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuRecyclerAdapter.MyViewHolder {
//        val binding= RegionListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return MyViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int =datalist.size
//
//    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
//    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
//    override fun onBindViewHolder(holder: GuRecyclerAdapter.MyViewHolder, position: Int) {
//        holder.bind(datalist[position])
////        holder.itemView.setOnClickListener {
////            itemClickListener.onClick(it,position)
////            row_index = position
////            notifyDataSetChanged()
////        }
//        if(row_index == position){
//            binding.
//        }
//        else{}
//    }
//    interface OnItemClickListener{
//        fun onClick(v: View, position: Int)
//    }
//    fun setItemClickListener(onItemClickListener: OnItemClickListener){
//        this.itemClickListener=onItemClickListener
//    }
//    private lateinit var itemClickListener: OnItemClickListener
//
