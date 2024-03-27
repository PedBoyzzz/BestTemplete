package com.aa.project2.api1.upload

import okhttp3.MultipartBody

class UpLoadHelper(val service: UploadService) {
   fun uploadImage(files:List<MultipartBody.Part>) = service.uploadImage(files)
   fun uploadImage(file:MultipartBody.Part) =service.uploadImage(file)
}