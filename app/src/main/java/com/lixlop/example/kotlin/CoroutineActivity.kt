package com.lixlop.example.kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.lixlop.example.R
import com.lixlop.example.retrofit2.RetrofitServiceGenerator
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody

/**
 * 协程：线程操作API，设计的初衷是为了解决并发问题
 * 1、所有代码跑在线程中，而线程跑在进程中
 * 2、协程跑在线程中
 * 3、Android系统上，如果在主线程进行网络请求，会抛出NetworkOnMainThreadException,对于在主线程上的协程也不例外，这种场景使用协程还是要切换线程。
 */
class CoroutineActivity : AppCompatActivity() {
    val liveData = MutableLiveData<String>()
    val activity: CoroutineActivity by lazy {
        this
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        val job = Job()
        val mScope = CoroutineScope(Dispatchers.Main + job)
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->

        }
        mScope.launch(exceptionHandler) {
            test2()
            val list = arrayListOf<Int>()
            supervisorScope {
                for (i in 0..100) {
                    async {
                        delay(3000)
                        list.add(i)
                    }
                }
            }
            for (temp in list) {
                Log.e("lixiaoyan", "$temp")
            }
        }
        layout.setOnClickListener {
            Log.e("lixiaoyan", "click")
        }
    }

    private suspend fun test2(){
//        withContext(Dispatchers.IO){
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
            Typeface.createFromAsset(baseContext.assets, "PingFang.ttc")
//        }
    }
    private suspend fun test(): String = withContext(Dispatchers.IO) {
        coroutineScope {
            launch {

            }
        }
        delay(3000)
        "123"
    }

    private fun coroutine() {
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
    }

    private suspend fun getImage(): Bitmap? {
        return withContext(Dispatchers.IO) {
            val response: Deferred<ResponseBody> =
                RetrofitServiceGenerator.createService(ImageServer::class.java)
                    .kwFetchImage("https://upload-images.jianshu.io/upload_images/11207702-2b20cfd4c8375a26.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/480/format/webp")
            var bitmap: Bitmap? = null
            val st = response.await()
            if (st.byteStream() != null) {
                bitmap = BitmapFactory.decodeStream(st!!.byteStream())
            }
            bitmap
        }
    }
}