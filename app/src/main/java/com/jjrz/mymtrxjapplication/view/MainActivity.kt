package com.jjrz.mymtrxjapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.jjrz.mymtrxjapplication.databinding.ActivityMainBinding
import com.jjrz.mymtrxjapplication.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        viewModel  = ViewModelProvider(this).get(MyViewModel::class.java)
        val adapter = MyListAdapter()
        binding.mainRecyclerview.adapter = adapter

        viewModel.postsList.observe(this) {
//            LogKitty("postslist changed ${viewModel.postsList.value}")
            if (viewModel.userList.value?.isNotEmpty() == true) {
                viewModel.mergeList()
            }
        }

        viewModel.userList.observe(this) {
//            LogKitty("userlist changed ${viewModel.userList.value}")
            if (viewModel.postsList.value?.isNotEmpty() == true) {
                viewModel.mergeList()
            }
        }

        viewModel.summaryList.observe(this) {
//            LogKitty("summarylist changed")
            viewModel.summaryList.value?.toMutableList()?.let { it1 -> adapter.updateList(it1) }
        }

        viewModel.getPosts()
        viewModel.getUsers()
//        adapter.addSample()

    }
}