package com.example.work2.api

import com.aa.project2.models.Model1
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface  ApiService2 {
    @GET("/photos")
    fun getUser(): Call<List<Model1>>
    @GET("/photos")
    fun getUser2(): Observable<Call<List<Model1>>>
}