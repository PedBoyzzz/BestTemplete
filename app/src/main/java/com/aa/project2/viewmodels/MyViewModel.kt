package com.aa.project2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aa.project2.models.Model1
import com.aa.project2.repostory.Repostory
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel:ViewModel {
    var result = MutableLiveData<List<Model1>>()
    lateinit var repostory: Repostory
    init {
        //System.out.println("init xxx "+repostory)
    }
   constructor(repostory: Repostory){
       this.repostory = repostory
       //System.out.println("init xxx "+repostory)
       //loadData()
       System.out.println("constructor xxx")
       testCoroutine()
   }
    fun runCoroutine(){

    }
    fun testCoroutine(){

        CoroutineScope(Dispatchers.IO).launch{
            //res.body()
            runCoroutine()

        }
    }
    fun loadData(){
        /*https://<DOMAIN>/api/users?userId=1&userId=2&userId=3
         it will be recieved as userId = [1,2,3].
         @GET("/api/users")
         public Call<UsersApiResponse> getUsers(@Query("userId") List<Integer> ids);

         ‘/api/users/1’.
         @GET("/api/users/{id}")
         public Call<UserApiResponse> getUser(@Path("id") Long id);
          */
        //แบบที่1 ท่า Retrofiltธรรมดา เรียกผ่าน Retrofit Method ของ Service มี Call
        val res = this.repostory.getModels()
        res.enqueue(object :Callback<List<Model1>>{
            override fun onResponse(call: Call<List<Model1>>, response: Response<List<Model1>>) {
                println("load data complete ")
                //this@MyViewModel.result.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Model1>>, t: Throwable) {
            }
        })
        //แบบที่2 ท่า Retrofiltธรรมดา เรียกผ่าน Rx Method ของ Service มี ไม่ต้องมี Call
        val res2 = this.repostory.getModels2()
        res2.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it->/*result.postValue(it)*/},{err->})
            //.subscribe( { it->println("----data size "+it.size)}, {})
        //แบบที่3 ท่า Retrofiltธรรมดา เรียกผ่าน CoroutineScope Method ของ Service มี ไม่ต้องมี Call แต่ function ต้องนำหน้าด้วย suspend
        CoroutineScope(Dispatchers.IO).launch {
            val res3 = repostory.getModels3()
            //result.postValue(res3.body())
        }
        //แบบที่4 ท่า Retrofiltธรรมดา เรียกผ่าน CoroutineScope เหมือนด้านบน แต่ response ไม่ห่อหุ้มด้วยอะไรเลย
        //val res4 = repostory.getModels4()
        CoroutineScope(Dispatchers.IO).launch {
            val res4 = repostory.getModels4()
            println("res 4 = "+res4)
        }
    }
    fun test(){
        Thread.sleep(1000L)
    }
}