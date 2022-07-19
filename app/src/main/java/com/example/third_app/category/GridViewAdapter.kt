package com.example.third_app.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.third_app.R

class GridViewAdapter(val context: Context, val img_list: Array<Int>, val text_list: Array<String>) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?) : View{
        val view: View = LayoutInflater.from(context).inflate(R.layout.category_list, null)
        view.findViewById<TextView>(R.id.tv_category_name).text = text_list[p0]
        view.findViewById<ImageView>(R.id.iv_category_icon).setImageResource(img_list[p0])
        return view
    }

    override fun getCount(): Int {
        return img_list.size
    }

    override fun getItem(p0: Int): Any {
        return text_list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }
}