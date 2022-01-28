package com.jjrz.mymtrxjapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjrz.mymtrxjapplication.utility.DebugHelper.Companion.LogKitty
import com.jjrz.mymtrxjapplication.databinding.ListItemBinding
import com.jjrz.mymtrxjapplication.model.Summary

class MyListAdapter : RecyclerView.Adapter<MyListAdapter.ListViewHolder>() {
    private var myDisplayList = mutableListOf<Summary>()

    fun updateList(f: MutableList<Summary>) {
        myDisplayList = f
        notifyDataSetChanged()
        LogKitty("adapted list updated")
    }

    inner class ListViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ListItemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder.binding) {
            textCompanyName.text =
                myDisplayList[position].companyname
            textTitle.text = myDisplayList[position].posttitle
            textBody.text = myDisplayList[position].postbody
        }
    }

    override fun getItemCount(): Int = myDisplayList.size

    fun addSample() {
        myDisplayList.add(Summary("test3", "test3", "test3"))
        myDisplayList.add(Summary("test4", "test4", "test4"))
        notifyDataSetChanged()
        LogKitty("myDisplayList size : $myDisplayList")
    }
}
