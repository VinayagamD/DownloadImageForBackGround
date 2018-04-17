package com.example.vinay.downloadimageforbackground

import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloTextView.setOnClickListener({
            val imageDownloader = ImageDownloader(this, object : ImageDownloader.ImageCallback {
                override fun onSuccessImage(drawable: BitmapDrawable) {
                    mainLayout.background = drawable
                }

                override fun onFailed(message: String?) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                }

            })
            DownloadApplication.getAppcontext().scheduler.execute(imageDownloader)
        })
    }
}
