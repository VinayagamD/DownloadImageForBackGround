package com.example.vinay.downloadimageforbackground

import android.os.Handler
import android.os.Message

abstract class WebService : Runnable {
    var isError: Boolean = false
    var message: String? = null
    var throwable:Throwable?= null

    protected abstract fun responseHandler(msg: Message)

    protected var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            this@WebService.responseHandler(msg)
        }
    }
}