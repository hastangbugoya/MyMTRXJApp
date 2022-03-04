package com.jjrz.mymtrxjapplication.network

import com.jjrz.mymtrxjapplication.model.PostsResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class PostRetrofit {

    private val postService = createRetrofit().create(PostService::class.java)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getPosts(): Single<PostsResponse> = postService.getPosts()

    interface PostService {
        @GET("posts")
        fun getPosts(): Single<PostsResponse>
    }

}