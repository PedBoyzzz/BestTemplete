package com.aa.project2.api1.shop

class HelperBase(val service:ServiceBase) {
    fun getProducts() = service.getProducts()
    fun getProducts2() = service.getProducts2()
    suspend fun getProducts3() = service.getProducts3()
}