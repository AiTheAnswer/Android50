package com.allen.recyclerviewdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.allen.recyclerviewdemo.R

class RecyclerViewAdapter(var mData: ArrayList<String>, var mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, null))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv.text = mData[position]
    }

    fun addItem() {
        mData.add(1, "2")
        notifyDataSetChanged()
    }

    fun removeItem() {
        if (mData.size > 0) {
            mData.removeAt(0)
            notifyDataSetChanged()
        } else {
            Toast.makeText(mContext, "无数据了，不能删除", Toast.LENGTH_SHORT).show()
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv: TextView = view.findViewById(R.id.item_tv)
    }

}