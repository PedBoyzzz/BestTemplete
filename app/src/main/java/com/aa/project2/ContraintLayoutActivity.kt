package com.aa.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ContraintLayoutActivity : AppCompatActivity() {
    lateinit var ib:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contrain)
        ib = findViewById(R.id.imageButton11)
        ib.setOnClickListener {
            println("click ib")
            //System.exit(0)
        }
    }
}