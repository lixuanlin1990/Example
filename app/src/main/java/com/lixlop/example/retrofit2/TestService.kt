package com.lixlop.example.retrofit2

import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.POST

interface TestService {
    @POST("/user/login")
    fun login(@Field("username") userName: String,
              @Field("password") passWord: String): Deferred<String>
}