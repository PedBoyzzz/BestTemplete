package com.aa.project2.repostory

import com.aa.project2.api1.upload.UpLoadHelper
import okhttp3.MultipartBody

class UploadRepostory(val helper:UpLoadHelper) {
    fun uploadImage(files:List<MultipartBody.Part>) = helper.uploadImage(files)
    fun uploadImage(file:MultipartBody.Part) = helper.uploadImage(file)
}