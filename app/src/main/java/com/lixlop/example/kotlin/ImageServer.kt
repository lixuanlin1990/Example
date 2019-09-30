package com.lixlop.example.kotlin

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageServer {
    @GET
    abstract fun kwFetchImage(@Url url: String): Call<ResponseBody>
}