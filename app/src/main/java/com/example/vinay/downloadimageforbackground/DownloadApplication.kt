package com.example.vinay.downloadimageforbackground

import android.app.Application
import java.security.AccessControlContext
import java.util.concurrent.*

class DownloadApplication:Application() {
    lateinit var scheduler: ThreadPoolExecutor


    override fun onCreate() {
        super.onCreate()
        try {
            appContext = this
            scheduler = ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 100000L,
                    TimeUnit.MILLISECONDS, LinkedBlockingQueue<Runnable>())
        } catch (t:Throwable) {

        }


    }

    companion object {
        private val CORE_POOL_SIZE = 3
        private val MAXIMUM_POOL_SIZE = 3
        private val DM = "DM"

        private lateinit var appContext: DownloadApplication
        fun getAppcontext() = appContext
    }

}