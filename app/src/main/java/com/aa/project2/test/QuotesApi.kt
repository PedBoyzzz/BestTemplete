package com.aa.project2.test
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("/quotes")
    fun getQuotes() :Call<QuoteList>
    @GET("/quotes")
    fun getQuotes2() :Response<QuoteList>
    @GET("/quotes")
    suspend fun getQuotes3() :QuoteList
    @GET("/quotes")
    fun getQuotes4() :Call<Response<QuoteList>>
    @GET("/quotes")
    fun getQuotes5() :Single<QuoteList>
}