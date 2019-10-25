package com.lixlop.example.kotlin

import androidx.lifecycle.Observer
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageServer {
    @GET
    fun kwFetchImage(@Url url: String): Deferred<ResponseBody>

    @GET
    suspend fun kwFetchImage1(@Url url: String): ResponseBody

    @GET
    fun kwFetchImage2(@Url url: String): Observer<ResponseBody>

    @GET
    fun kwFetchImage3(@Url url: String): Call<ResponseBody>
}