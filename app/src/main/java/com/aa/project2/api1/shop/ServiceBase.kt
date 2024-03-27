package com.aa.project2.api1.shop

import com.aa.project2.models.Products
import retrofit2.Call
import retrofit2.http.GET

interface ServiceBase {
    @GET("/products")
    fun getProducts():Call<Products>
}