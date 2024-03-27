package com.aa.project2

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class TestActivity : AppCompatActivity() {
    lateinit var img:ImageView
    lateinit var tv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t)
        tv = findViewById(R.id.tv11)
        img = findViewById(R.id.img12)
        //img.setImageDrawable(getDrawable(R.drawable.p1))
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                val getpermission = Intent()
                getpermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(getpermission);
            }
            System.out.println("eiei")
        }
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val filename = "136442.png"
        val file = File(sd, filename)
        System.out.println("file = ")

        if (file.exists()) {
            System.out.println("file xxx= " + file.absolutePath)
            val myBitmap = BitmapFactory.decodeFile(file.absolutePath)
            //System.out.println("image w = "+myBitmap.width)
            if (myBitmap == null) {
                System.out.println("image is null")
            } else {
                System.out.println("image is not null "+myBitmap.width)
                img.setImageBitmap(myBitmap)
            }
            //System.out.println("image is null "+myBitmap.width)
            //System.out.println("have file "+file.length()+" len = "+myBitmap.width)


        } else {
            System.out.println("file not found xxx")
        }
        t ({ i -> })
    }



    fun t(l: (Int) -> Unit){

    }
}