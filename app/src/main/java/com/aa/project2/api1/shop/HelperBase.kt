package com.aa.project2.api1.shop

class HelperBase(val service:ServiceBase) {
    fun getProducts() = service.getProducts()
}