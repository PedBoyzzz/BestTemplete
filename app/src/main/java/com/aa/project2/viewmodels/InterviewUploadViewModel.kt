package com.aa.project2.viewmodels

import android.graphics.BitmapFactory
import android.os.Environment
import androidx.lifecycle.ViewModel
import com.aa.project2.api1.upload.UploadRetrofit
import com.aa.project2.repostory.UploadRepostory
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class InterviewUploadViewModel(val repostory: UploadRepostory):ViewModel(){
    init {
        //upload2({it-> })
    }

    fun upload2(tempFilesList:ArrayList<File>,resultFromServ:(ResponseBody?)->Unit) {

       /*
        val sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val filename = "136442.png"
        val file = File(sd, filename)
        System.out.println("file size = "+file.length())
        val myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
        if(myBitmap == null){
            System.out.println("-----Upload image is null ")
        }
        val tempFilesList = ArrayList<File>()
        tempFilesList.add(file)
        //tempFilesList.add(file)

        */
        val images = ArrayList<MultipartBody.Part>()
        var i = 0

        /*
        //อัพโหลดรูปเดียว ok
        val requestBody2:RequestBody = RequestBody.create(MediaType.parse("image/jpg"),tempFilesList.get(0))
        val body2:MultipartBody.Part = MultipartBody.Part.createFormData("file", tempFilesList.get(0).name,requestBody2)
        val call = repostory.uploadImage(body2)
        */

        for (file in tempFilesList) {
            val filename = "file" + (i++)
            val requestFile = RequestBody.create(MediaType.parse("image/jpg"), file)
            val mp = MultipartBody.Part.createFormData("file",file.getName(), requestFile);
            images.add(mp)
        }
        val call = repostory.uploadImage(images)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                System.out.println("upload2 complete "+response.code())
                val  res = response.body()
                println("----res2 interview zzz= "+response)
                if(res==null){
                    resultFromServ(null)
                }else{
                    resultFromServ(res)
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                System.out.println("upload error eiei"+t.message)
                resultFromServ(null)
            }
        })
    }

}