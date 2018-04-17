package com.example.vinay.downloadimageforbackground

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Message
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageDownloader(val context: Context, val callback: ImageCallback) : WebService() {




    val file = File(context.filesDir,"myimage.jpg")
    val imageUrl ="https://i.imgur.com/aGfNKiQ.jpg"

    override fun responseHandler(msg: Message) {
        when(isError){
            true -> {
                callback.onFailed(message)
            }
            else ->{
                callback.onSuccessImage(BitmapDrawable(context.resources, BitmapFactory.decodeFile(file.absolutePath)))
            }
        }

    }

    override fun run() {

        try{

            if (file.exists()){
                isError = false
                mHandler.sendEmptyMessage(0)
                return
            }
            val client = OkHttpClient()
            val request = Request.Builder().url(imageUrl).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    try {
                        when {
                            response == null -> {
                                isError = true
                                message = "Empty Data"
                                mHandler.sendEmptyMessage(0)
                            }
                            response.isSuccessful -> {
                                response.body()?.byteStream().use { input ->
                                    val fos = FileOutputStream(file)
                                    fos.use {
                                        input?.copyTo(it)
                                        isError = false
                                        message = "success"
                                        mHandler.sendEmptyMessage(0)
                                    }
                                }

                            }
                            else -> {
                                isError = true
                                message = "Empty Data"
                                mHandler.sendEmptyMessage(0)
                            }

                        }
                    } catch (e: Throwable) {
                        isError = true
                        throwable = e
                        message = e.message
                        mHandler.sendEmptyMessage(0)
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    isError = true
                    throwable = e
                    message = e?.message
                    mHandler.sendEmptyMessage(0)
                }

            })
        }catch (t:Throwable){
            isError = true
            throwable = t
            message = t.message
            mHandler.sendEmptyMessage(0)
        }

    }


    interface ImageCallback{
       fun onSuccessImage(drawable: BitmapDrawable)
       fun onFailed(message:String?)
   }
}