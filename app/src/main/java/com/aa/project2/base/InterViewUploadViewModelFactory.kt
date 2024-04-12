package com.aa.project2.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aa.project2.api1.upload.UpLoadHelper
import com.aa.project2.repostory.Repostory
import com.aa.project2.repostory.UploadRepostory
import com.aa.project2.viewmodels.InterviewUploadViewModel
import com.aa.project2.viewmodels.MyViewModel
import com.aa.project2.viewmodels.UploadViewModel
import com.example.work2.api.ApiHelper

class InterViewUploadViewModelFactory(val helper: UpLoadHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InterviewUploadViewModel::class.java)) {
            return InterviewUploadViewModel(UploadRepostory(helper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}