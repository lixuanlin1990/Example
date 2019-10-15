package com.lixlop.example.kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lixlop.example.R
import com.lixlop.example.retrofit2.RetrofitServiceGenerator
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * 协程：线程操作API，设计的初衷是为了解决并发问题
 * 1、所有代码跑在线程中，而线程跑在进程中
 * 2、协程跑在线程中
 * 3、Android系统上，如果在主线程进行网络请求，会抛出NetworkOnMainThreadException,对于在主线程上的协程也不例外，这种场景使用协程还是要切换线程。
 */
class CoroutineActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        //方法一，runBlocking顶层函数
        runBlocking {

        }
        //方法二，使用GlobalScope单例对象
        GlobalScope.launch {

        }
        //方法三，通过CoroutineContext创建一个CoroutineScope对象
        val job = Job()
        val coroutineScope = CoroutineScope(Dispatchers.Main + job)
        Log.e("lixiaoyan", "$coroutineScope")
        coroutineScope.launch {
            Log.e("lixiaoyan", "$this")
            Log.e("lixiaoyan", "${Thread.currentThread().name}")
            val img = getImage()
            val result1 = async {
                Log.e("lixiaoyan", "$this")
                Log.e("lixiaoyan", "${Thread.currentThread().name}")
                delay(5000)
                1
            }
            val result2 = async {
                Log.e("lixiaoyan", "${Thread.currentThread().name}")
                delay(2000)
                2
            }
            Log.e("lixiaoyan", "${result1.await()}")
            Log.e("lixiaoyan", "${result2.await()}")
            image.setImageBitmap(img)
        }
        layout.setOnClickListener {
            Log.e("lixiaoyan", "点击")
        }
    }

    private suspend fun getImage(): Bitmap? {
        return withContext(Dispatchers.IO) {
            val response: Response<ResponseBody> =
                RetrofitServiceGenerator.createService(ImageServer::class.java)
                    .kwFetchImage("https://upload-images.jianshu.io/upload_images/11207702-2b20cfd4c8375a26.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/480/format/webp")
                    .execute()
            var bitmap: Bitmap? = null
            if (response.body()?.byteStream() != null) {
                bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
            }
            bitmap
        }
    }


}