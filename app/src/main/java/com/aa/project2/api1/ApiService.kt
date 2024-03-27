package com.aa.project2.api1
import com.aa.project2.models.Model1
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
interface ApiService {
    @GET("/photos")
    fun getModels(): Call<List<Model1>>
    @GET("/photos")
    fun getModels2(): Single<List<Model1>>
    @GET("/photos")
    suspend fun getModels3():Response<List<Model1>>
    @GET("/photos")
    suspend fun getModels4():List<Model1>
}