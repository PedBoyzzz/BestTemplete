package com.aa.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.aa.project2.models.Product
import com.bumptech.glide.Glide

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val product = intent.getSerializableExtra("product") as Product
        print("----product = "+product.title)
        img = findViewById(R.id.img)
        Glide.with(this)
            .load(product.thumbnail)
            //.override(200,200)
            .into(img)
    }
}