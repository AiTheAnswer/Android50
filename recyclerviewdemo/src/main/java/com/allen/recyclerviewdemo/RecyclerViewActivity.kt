package com.allen.recyclerviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.allen.recyclerviewdemo.adapter.RecyclerViewAdapter

class RecyclerViewActivity : AppCompatActivity() ,View.OnClickListener{
    override fun onClick(p0: View?) {

    }

    private var mBtnAdd: Button? = null
    private var mBtnRemove: Button? = null
    private var mBtnOrientation: Button? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerViewAdapter? = null
    private var mData: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        supportActionBar?.title = "RecyclerViewDemo"
        initView()
        initData()
        initListener()
        useRecyclerView()
    }

    private fun initListener() {
       mBtnAdd?.setOnClickListener(this)
        mBtnOrientation?.setOnClickListener(this)

    }

    private fun initData() {
        mData = ArrayList()
        for (i in 'A'..'z') {
            mData?.add("$i")
        }
    }


    private fun useRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        //设置布局管理器
        mRecyclerView?.layoutManager = layoutManager
        //设置item增加和删除时的动画
        mRecyclerView?.itemAnimator = DefaultItemAnimator()
        mAdapter = RecyclerViewAdapter(mData!!, this)
        //设置适配器
        mRecyclerView?.adapter = mAdapter
    }

    private fun initView() {
        mBtnAdd = findViewById(R.id.btn_add)
        mBtnRemove = findViewById(R.id.btn_remove)
        mBtnOrientation = findViewById(R.id.btn_orientation)
        mRecyclerView = findViewById(R.id.recycler_view)
    }
}
