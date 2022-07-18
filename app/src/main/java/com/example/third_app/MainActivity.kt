package com.example.third_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.third_app.category.CategoryFragment
import com.example.third_app.home.AddressSearchActivity
import com.example.third_app.home.HomeFragment
import com.example.third_app.login.LoginActivity
import com.example.third_app.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kakao.sdk.common.util.Utility
class MainActivity : AppCompatActivity() {
//    //mainActivity ItemViewModel
//    lateinit var viewModel : ItemViewModel

    // 타 액티비티에서 맥락 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView
        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_home -> {
                        // 다른 프래그먼트 화면으로 이동하는 기능
                        val homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, homeFragment).commit()
                    }
                    R.id.main_category -> {
                        val categoryFragment = CategoryFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, categoryFragment).commit()
                    }
                    R.id.main_heart-> {
                        val heartFragment = HeartFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, heartFragment).commit()
                    }
                    R.id.main_user-> {
                        val userFragment = UserFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fl_container, userFragment).commit()
                    }
                }
                true
            }
            selectedItemId = R.id.main_home
        }

        var toolBar = findViewById<ImageView>(R.id.toolbarAddress)
        toolBar.setOnClickListener{
            val intent = Intent(this, AddressSearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

    }
    //item 버튼 메뉴 Toolbar에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

}
