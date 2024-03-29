package com.lixlop.example.kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import com.lixlop.example.R
import com.lixlop.example.retrofit2.RetrofitServiceGenerator
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 协程：线程操作API，设计的初衷是为了解决并发问题
 * 1、所有代码跑在线程中，而线程跑在进程中
 * 2、协程跑在线程中
 * 3、Android系统上，如果在主线程进行网络请求，会抛出NetworkOnMainThreadException,对于在主线程上的协程也不例外，这种场景使用协程还是要切换线程。
 */
class CoroutineActivity : AppCompatActivity() {

    val mResult: LiveData<String> = liveData {
        delay(5000)
        emit("123")
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        lifecycleScope.launchWhenResumed{
            test3()
        }
    }

    private suspend fun test3(){
        Log.i("lixiaoyan","1")
        try {
            supervisorScope {
                Log.i("lixiaoyan","2")
//                val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//                }
                launch() {
                    Log.i("lixiaoyan","3")
                    launch {
                        Log.i("lixiaoyan","4")
                        delay(100)
                        throw ArithmeticException("Hey!!")
                    }
                    Log.i("lixiaoyan","5")
                }
                Log.i("lixiaoyan","6")
                val job = launch {
                    Log.i("lixiaoyan","7")
                    delay(1000)
                }
                try {
                    Log.i("lixiaoyan","8")
                    job.join()
                    Log.i("lixiaoyan","9")
                }catch (e:Exception){
                    Log.i("lixiaoyan","10.$e")
                }
            }
            Log.i("lixiaoyan","11")
        }catch (e:Exception){
            Log.i("lixiaoyan","12.$e")
        }
        Log.i("lixiaoyan","13")
    }

    private suspend fun test2(){
        coroutineScope {
            delay(5000)
            Log.e("lixiaoyan","12345")
        }
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

        val result = suspendCoroutine<String> {
            it.resume("1")
            it.resumeWithException(Exception())
        }
    }
}