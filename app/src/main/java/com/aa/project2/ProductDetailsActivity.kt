package com.aa.project2

import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.CaseMap.Title
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aa.project2.adapter.ListAdapter2
import com.aa.project2.api1.upload.UpLoadHelper
import com.aa.project2.api1.upload.UploadRetrofit
import com.aa.project2.base.InterViewUploadViewModelFactory
import com.aa.project2.models.ImageItem
import com.aa.project2.models.Product
import com.aa.project2.viewmodels.InterviewUploadViewModel
import com.aa.project2.viewmodels.UploadViewModel
import com.bumptech.glide.Glide
import java.io.File

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var img: ImageView
    lateinit var title: TextView
    lateinit var openGall: Button
    lateinit var upload: Button
    lateinit var listView: RecyclerView
    lateinit var images: ArrayList<ImageItem>
    lateinit var adapter: ListAdapter2
    lateinit var interviewUploadViewModel: InterviewUploadViewModel
    lateinit var pb:ProgressBar
    lateinit var files:ArrayList<File>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        files = ArrayList()
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                val getpermission = Intent()
                getpermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(getpermission);
            }
            System.out.println("eiei")
        }

        listView = findViewById(R.id.myList)
        val layoutManager = LinearLayoutManager(applicationContext)
        listView.layoutManager = layoutManager

        images = ArrayList()
        adapter = ListAdapter2(images)
        listView.adapter = adapter
        listView.adapter?.notifyDataSetChanged()

        val product = intent.getSerializableExtra("product") as Product
        print("----product = " + product.title)
        img = findViewById(R.id.img)
        upload = findViewById(R.id.upload)
        openGall = findViewById(R.id.open_gall)
        Glide.with(this)
            .load(product.thumbnail)
            //.override(200,200)
            .into(img)
        title = findViewById(R.id.title)
        title.text = product.title

        openGall.setOnClickListener {
            selectImageInAlbum()
        }
        pb = findViewById(R.id.progressBar)
        upload.setOnClickListener {
            if(files.size==0){
                return@setOnClickListener
            }
            pb.visibility = View.VISIBLE
            interviewUploadViewModel.upload2 (files,{ it->
                println("upload result = "+it?.string())
                pb.visibility = View.GONE
                files.clear()
                images.clear()
                adapter.notifyDataSetChanged()
                if (it != null) {
                    showAlert("","Upload complete.")
                }else{
                    showAlert("Error","Upload error try again.")
                }
            })
        }
        initViewModel()
    }

    fun selectImageInAlbum() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (data != null) {
                val selectedImage = data?.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val mediaPath = cursor.getString(columnIndex)
                var f = File(mediaPath)
                var filename = f.getName()

                files.add(f)
                val img =
                    BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage))
                cursor.close()
                val imageItem = ImageItem(img, filename,f)
                images.add(imageItem)
                adapter.notifyDataSetChanged()
                println("image width from gall = " + f.absoluteFile)
            }
        }
    }

    fun initViewModel() {
        interviewUploadViewModel = ViewModelProviders.of(
        this,
            InterViewUploadViewModelFactory(UpLoadHelper(UploadRetrofit.apiService))
        ).get(InterviewUploadViewModel::
        class.java)
    }
    fun showAlert(title:String,msg:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("Ok") { dialog, which ->
        }
        /*builder.setNegativeButton("No") { dialog, which ->
        }*/
        /*
        builder.setNeutralButton("Maybe") { dialog, which ->
        }
         */
        builder.show()
    }
}