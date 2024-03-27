package com.example.work2.api

import com.aa.project2.api1.ApiService
import javax.inject.Inject

class ApiHelper (private val apiService: ApiService) {
     fun getModels() = apiService.getModels()
     fun getModels2() = apiService.getModels2()
     suspend fun getModels3() = apiService.getModels3()
     suspend fun getModels4() = apiService.getModels4()
}