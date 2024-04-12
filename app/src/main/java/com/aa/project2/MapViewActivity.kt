package com.aa.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aa.project2.fragments.HomeFragment

class MapViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        val fragmentManager = this@MapViewActivity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val homeFragment = HomeFragment.newInstance(null,null)
        //System.out.println("show mapview")
        fragmentTransaction.add(R.id.linear1, homeFragment)//ใช้แค่ตอน add ครั้งแรก
        fragmentTransaction.commit()//ใช้แค่ตอน add ครั้งแรก
    }
}