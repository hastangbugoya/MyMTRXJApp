package com.jjrz.mymtrxjapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjrz.mymtrxjapplication.utility.DebugHelper.Companion.LogKitty
import com.jjrz.mymtrxjapplication.model.PostsResponseItem
import com.jjrz.mymtrxjapplication.model.Summary
import com.jjrz.mymtrxjapplication.model.UserResponseItem
import com.jjrz.mymtrxjapplication.network.PostRetrofit
import com.jjrz.mymtrxjapplication.network.UserRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val compositeDisposable2 = CompositeDisposable()
    private val userRetrofit = UserRetrofit()
    private val postsRetrofit = PostRetrofit()
    val userList = MutableLiveData<List<UserResponseItem>>()
    val postsList = MutableLiveData<List<PostsResponseItem>>()
    var summaryList = MutableLiveData<List<Summary>>()

    fun getUsers() {
        compositeDisposable.add(
            userRetrofit.getUsers()
                .subscribeOn(
                    Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    userList.postValue(response)
                    compositeDisposable.clear()
                }, { throwable ->
                    compositeDisposable.clear()
                    LogKitty(throwable.toString())
                })
        )
    }

    fun getPosts() {
        compositeDisposable2.add(
            postsRetrofit.getPosts()
                .subscribeOn(
                    Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    postsList.postValue(response)
                    compositeDisposable2.clear()
                }, { throwable ->
                    compositeDisposable2.clear()
                    LogKitty(throwable.toString())
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable2.clear()
        super.onCleared()
    }

    fun mergeList() {
        val tempList = mutableListOf<Summary>()
        LogKitty("postsList size :  ${postsList.value?.size}")
        userList.value?.forEach { userResponseItem ->
            val list = postsList.value?.filter {
                it.userId?.equals(userResponseItem.id) == true
            }
            LogKitty("Filtered list size : ${list?.size}")
            list?.forEach {
                tempList.add(Summary(userResponseItem.company?.name, it.title, it.body))
            }
        }
        summaryList.value = tempList
    }
}