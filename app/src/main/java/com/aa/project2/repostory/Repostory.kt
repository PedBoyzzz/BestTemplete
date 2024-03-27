package com.aa.project2.repostory

import com.example.work2.api.ApiHelper

class Repostory(val helper: ApiHelper) {
    fun getModels() = helper.getModels()
    fun getModels2() = helper.getModels2()
    suspend fun getModels3() = helper.getModels3()
    suspend fun getModels4() = helper.getModels4()
}