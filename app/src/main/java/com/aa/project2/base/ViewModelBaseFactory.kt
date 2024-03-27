package com.aa.project2.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aa.project2.api1.shop.HelperBase
import com.aa.project2.repostory.RepostoryBase
import com.aa.project2.viewmodels.ViewModelBase

class ViewModelBaseFactory(val helper: HelperBase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelBase::class.java)) {
            return ViewModelBase(RepostoryBase(helper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}