package com.jjrz.mymtrxjapplication.network

import com.jjrz.mymtrxjapplication.model.UserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class UserRetrofit {

    private val userService = createRetrofit().create(UserService::class.java)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getUsers(): Single<UserResponse> = userService.getUsers()

    interface UserService {
        @GET("users")
        fun getUsers(): Single<UserResponse>
    }

}