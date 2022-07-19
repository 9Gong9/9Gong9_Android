package com.example.third_app.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.example.third_app.MainActivity
import com.example.third_app.R
import com.example.third_app.databinding.FragmentCategoryBinding
import com.example.third_app.home.HomeFragment


class CategoryFragment : Fragment() {

    private lateinit var binding : FragmentCategoryBinding
    private val TAG: String = CategoryFragment::class.java.getSimpleName()

    private var gridview: GridView? = null
    // Context를 액티비티로 형변환해서 할당
    lateinit var mainActivity: MainActivity
        override fun onAttach(context: Context) {
            super.onAttach(context)
            mainActivity = context as MainActivity
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        gridview = binding.categoryGridview

        val img = arrayOf(
            R.drawable.fruits,
            R.drawable.vegetable,
            R.drawable.grain,
            R.drawable.beef,
            R.drawable.fish,
            R.drawable.milk,
            R.drawable.refrigerator,
            R.drawable.wine,
            R.drawable.kimchi,
            R.drawable.latte,
            R.drawable.ramen,
            R.drawable.mustard,
            R.drawable.chips,
            R.drawable.toast,
            R.drawable.whey,
            R.drawable.green
        )

        val txt = arrayOf(
            "과일",
            "채소",
            "쌀/잡곡/견과",
            "정육/계란류",
            "수산물/건해산",
            "우유/유제품/유아식",
            "냉장/냉동/간편식",
            "생수/음료/주류",
            "밀키트/김치/반찬",
            "커피/원두/차",
            "라면/면류/즉석식품/통조림",
            "장류/양념/가루/오일",
            "과자/시리얼/빙과/떡",
            "베이커리/잼/샐러드",
            "건강식품",
            "친환경/유기농"
        )

        val path = arrayOf(
            "과일",
            "채소",
            "쌀잡곡견과",
            "정육계란류",
            "수산물건해산",
            "우유유제품유아식",
            "냉장냉동간편식",
            "생수음료주류",
            "밀키트김치반찬",
            "커피원두차",
            "라면면류즉석식품통조림",
            "장류양념가루오일",
            "과자시리얼빙과떡",
            "베이커리잼샐러드",
            "건강식품",
            "친환경유기농"
        )

        //리스트뷰에 Adapter 설정
        val gridViewAdapter = GridViewAdapter(mainActivity, img, txt)
        gridview?.adapter = gridViewAdapter

        //
        gridview!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(a_parent: AdapterView<*>?, a_view: View?, a_position: Int, a_id: Long) {
                val item : String = gridViewAdapter.getItem(a_position) as String
                Toast.makeText(
                    mainActivity,
                    item + " Click event",
                    Toast.LENGTH_SHORT
                ).show()
                CategoryApplication.setCategoryId(path[a_position])
                CategoryApplication.setCategoryRealId(txt[a_position])
                val homeFragment = HomeFragment()
                mainActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, homeFragment).commit()
            }
        })

        binding.btnCategoryAll.setOnClickListener{
            CategoryApplication.deleteCategoryId()
            CategoryApplication.deleteCategoryRealId()
            val homeFragment = HomeFragment()
            mainActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, homeFragment).commit()
        }

        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}