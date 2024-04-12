package com.aa.project2.api1.shop

import com.aa.project2.models.Model1
import com.aa.project2.models.Products
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ServiceBase {
    @GET("/products")
    fun getProducts():Call<Products>
    @GET("/products")
    fun getProducts2(): Single<Products>
    @GET("/products")
    suspend fun getProducts3():Products
}