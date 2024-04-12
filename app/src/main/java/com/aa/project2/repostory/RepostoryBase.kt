package com.aa.project2.repostory

import com.aa.project2.api1.shop.HelperBase
import com.example.work2.api.ApiHelper

class RepostoryBase(val helper: HelperBase) {
     fun getProducts() = helper.getProducts()
     fun getProducts2() = helper.getProducts2()
     suspend fun getProducts3() = helper.getProducts3()
}