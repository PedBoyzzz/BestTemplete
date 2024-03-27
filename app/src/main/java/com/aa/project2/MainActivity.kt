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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aa.project2.api1.shop.HelperBase
import com.aa.project2.api1.shop.RetrofitBase
import com.aa.project2.api1.upload.UpLoadHelper
import com.aa.project2.api1.upload.UploadRetrofit
import com.aa.project2.base.MainViewModelFactory
import com.aa.project2.base.UploadViewModelFactory
import com.aa.project2.base.ViewModelBaseFactory
import com.aa.project2.models.Product
import com.aa.project2.viewmodels.MyViewModel
import com.aa.project2.viewmodels.UploadViewModel
import com.aa.project2.viewmodels.ViewModelBase
import com.example.work2.api.ApiHelper
import com.example.work2.api.RetrofitBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class MainActivity : AppCompatActivity(),ListAdapter.Listener {
    lateinit var myViewModel: MyViewModel
    lateinit var myViewModel2:ViewModelBase
    lateinit var uploadViewModel: UploadViewModel
    lateinit var tv:TextView
    lateinit var img:ImageView
    lateinit var list:RecyclerView
    lateinit var adapter:ListAdapter
    lateinit var myList:ArrayList<String>
    fun myList(list:List<String>){
        for(item in list){

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModels()
        myList = ArrayList<String>()
        myList.add("Niwat")
        myList(myList)
        testCoroutine()
        tv = findViewById(R.id.tv)
        list = findViewById(R.id.list)
        img = findViewById(R.id.img)
        val layoutManager = LinearLayoutManager(applicationContext)
        list.layoutManager = layoutManager
        System.out.println("push")
        //imageFromFile()

        //MultipartBody.Part



        /*val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        quotesApi.getQuotes().enqueue(object: Callback<QuoteList> {
            override fun onResponse(
                call: Call<QuoteList>,
                response: Response<QuoteList>
            ) {
                System.out.println("success ha "+response.body()?.count)
                println("------reponse = "+response?.body())
            }
            override fun onFailure(call: Call<QuoteList>?, t: Throwable?) {
            }
        })
        quotesApi.getQuotes4().enqueue(object :Callback<Response<QuoteList>>{
            override fun onResponse(
                call: Call<Response<QuoteList>>,
                response: Response<Response<QuoteList>>
            ) {
                response.body()?.body()?.count
            }
            override fun onFailure(call: Call<Response<QuoteList>>, t: Throwable) {
            }

        })

        try {
            println("------ error stack")
            //quotesApi.getQuotes3()
        }catch (e:Exception){
            e.printStackTrace()
        }
        //เรียกผ่าน Coroutine reponse ไม่ต้องห่อหุ้มอะไรของ retrofitเลย แต่ต้องนำหน้า function ด้วย suspend เสมอ
        /*

         */
        tv.text = "Test"
        CoroutineScope(Dispatchers.IO).launch {
            test2()
            tv.text = "Test"
            print("--update ui")
            //System.exit(0)
            quotesApi.getQuotes3()
            withContext(Dispatchers.Main){
                //tv.text = "Test"
            }
        }
        //ถ้าเรียกผ่าน Rx อย่าลืม Retrofit ต้องใส่ addCallAdapterFactory(RxJava2CallAdapterFactory.create()) ตอน create
        //ไม่งั้น error
        val result5 = quotesApi.getQuotes5()
        result5.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{})
        //Test simple rx
        val rx = Observable.just("")
        rx.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{},{})

        //Get reponse Data from ViewModel
        //MutableLiveData Observ
        tv.text = ""
        myViewModel.result.observe(this,{
            System.exit(0)
            tv.text = "รับข้อมูลจาก CoroutineScope สำเหร็จ จำนวน = "+it.size
        })

         */
        //imageFromFile()
        upload()
        //upload2()
    }
    fun imageFromFile(){
        //android:requestLegacyExternalStorage="true"
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                val getpermission = Intent()
                getpermission.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(getpermission);
            }
            System.out.println("eiei")
        }
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val filename = "android.png"
        val file = File(sd, filename)
        System.out.println("image size = "+file.length())
        val myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
        img.setImageBitmap(myBitmap)
        System.out.println("image w = "+myBitmap.width)
    }
    fun upload(){
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val filename = "android.png"
        val file = File(sd, filename)

        val size = RequestBody.create(MediaType.parse("text/plain"), file.length().toString())
        val contentType = RequestBody.create(MediaType.parse("text/plain"), "application/pdf")

        // Create a multipart request body part for the file

        val requestBody1:RequestBody = RequestBody.create(MediaType.parse("application/pdf"),file)
        val body1:MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name,requestBody1)
        //var param = ["reqtype":"fileupload"]

        val requestBody2:RequestBody = RequestBody.create(MediaType.parse("image/jpg"),file)
        val body2:MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name,requestBody2)
        //Parameter "name" ของ method ข้างบนมีผลต่อการ Upload

        // Make the API call
        System.out.println("start upload")
        val service = UploadRetrofit.apiService
        val call = service.uploadImage(body2)
        call.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //System.out.println("upload1 complete "+response.body())
                System.out.println("upload1 complete1 "+response.code())
                val res = response.body()
                println("----res1 code zzz= "+response)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                System.out.println("upload error xxx"+t.message)
            }
        })

    }
    fun upload2() {
        val service = UploadRetrofit.apiService
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val filename = "136442.png"
        val file = File(sd, filename)

        val tempFilesList = ArrayList<File>()
        tempFilesList.add(file)
        tempFilesList.add(file)
        val images = ArrayList<MultipartBody.Part>()
        var i = 0
        for (f in tempFilesList) {
            val filename = "file"+(i++)
            val requestFile = RequestBody.create(MediaType.parse("image/jpg"), f)
            val mp = MultipartBody.Part.createFormData("image", f.getName(), requestFile);
            images.add(mp)
        }
        val call = service.uploadImage(images)
        call.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                System.out.println("----res2 code xxx= "+response.code())
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                System.out.println("upload2 error xxx"+t.message)
            }
        })
    }

    suspend fun test2(){

    }
    fun setViewModels(){
        /*ถ้าฟ้องerror ไม่รู้จัก ViewModelProviders ให้ add ใน gradle แบบนี้
        //LifeCycle
        implementation 'androidx.lifecycle:lifecycle-extensions:2.1.0'
        implementation 'androidx.lifecycle:lifecycle-common:2.2.0'
        implementation 'androidx.lifecycle:lifecycle-runtime:2.2.0'
        implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
         */
        // ViewModelProviders
        myViewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MyViewModel::class.java)
       myViewModel.loadData()
        myViewModel.result.observe(this,{
            println("-----result len = "+it.size)
        })

        myViewModel2 = ViewModelProviders.of(
            this,
            ViewModelBaseFactory(HelperBase(RetrofitBase.apiService))
        ).get(ViewModelBase::class.java)
        myViewModel2.result.observe(this,{
            adapter = ListAdapter(it.products,this,this)
            list.adapter = adapter
        })

        uploadViewModel = ViewModelProviders.of(
            this,
            UploadViewModelFactory(UpLoadHelper(UploadRetrofit.apiService))
        ).get(UploadViewModel::class.java)

        test()
    }
    fun test(){
        GlobalScope.launch {
            testCoroutine()
        }
    }
    fun testCoroutine():Unit{
        Thread.sleep(1000L)
        println("-----run Coroutine")
    }

    override fun onSelect(pos: Int,product: Product) {
        System.out.println(" sselect at "+pos)
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product",product)
        startActivity(intent)
    }
}