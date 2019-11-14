package com.lixlop.example.lottie

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.FontAssetDelegate
import com.airbnb.lottie.TextDelegate
import com.lixlop.example.R
import kotlinx.android.synthetic.main.activity_lottie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LottieActivity : AppCompatActivity() {
    private suspend fun fetchFont() =
        withContext(Dispatchers.IO) { Typeface.createFromAsset(baseContext.assets, "PingFang.ttc") }

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        GlobalScope.launch {
            val typeface = fetchFont()
            ////assets目录下的子目录，存放动画所需的图片
            image.imageAssetsFolder = "images/launch/"
            //在assets目录下的动画json文件名
            image.setAnimation("data.json")
            image.setFontAssetDelegate(object : FontAssetDelegate() {
                override fun fetchFont(fontFamily: String?): Typeface {
                    return typeface
                }
            })
            val textDelegate = TextDelegate(image)
            textDelegate.setText("小伙伴", "大头")
            textDelegate.setText("小伙伴...", "费劲")
            image.setTextDelegate(textDelegate)
            image.setImageAssetDelegate {
                Log.e("lixiaoyan", "${it.dirName} ${it.fileName} ${it.id}")
                if (it.id == "image_0") {
                    return@setImageAssetDelegate BitmapFactory.decodeResource(
                        resources,
                        R.mipmap.ic_launcher_round
                    )
                }
                val inputStream = baseContext.assets.open("${image.imageAssetsFolder}${it.fileName}")
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                return@setImageAssetDelegate bitmap
            }
            //播放动画
            image.playAnimation()
        }
    }
}