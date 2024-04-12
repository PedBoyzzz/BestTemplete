package com.aa.project2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aa.project2.models.Products
import com.aa.project2.repostory.RepostoryBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelBase(val repostoryBase: RepostoryBase): ViewModel() {
    var result = MutableLiveData<Products>()
    init {
        System.out.println("init ViewModelBase "+repostoryBase)
        loadData()
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
        repostoryBase.getProducts().enqueue(object :Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                System.out.println("onResponse "+ (response.body()?.products?.size))
               //result.postValue(response.body())
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {

            }

        })
         repostoryBase.getProducts2().subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({/*result.postValue(it)*/},{})
         CoroutineScope(Dispatchers.IO).launch {
            val res = repostoryBase.getProducts3()
             System.out.println("onResponse3 = "+res.products.size)
             result.postValue(res)
         }
    }
}