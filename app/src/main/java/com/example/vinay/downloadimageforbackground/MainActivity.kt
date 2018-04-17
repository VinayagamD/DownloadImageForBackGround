package com.example.vinay.downloadimageforbackground

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vinaylogics.imageurltodrawable.ImageDownloader
import com.vinaylogics.urltodrwable.UrlDownloadApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloTextView.setOnClickListener({
            val imageDownloader = ImageDownloader(this, "https://i.imgur.com/aGfNKiQ.jpg", "downloadable.jpg" ,object : ImageDownloader.ImageCallback {
                override fun onSuccessImage(drawable: BitmapDrawable) {
                    mainLayout.background = drawable
                }

                override fun onFailed(message: String?) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                }

            })
            UrlDownloadApplication.getAppcontext().scheduler.execute(imageDownloader)
        })
    }
}
