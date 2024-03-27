package com.aa.project2.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aa.project2.repostory.Repostory
import com.aa.project2.viewmodels.MyViewModel
import com.example.work2.api.ApiHelper

class MainViewModelFactory(val helper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(Repostory(helper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}