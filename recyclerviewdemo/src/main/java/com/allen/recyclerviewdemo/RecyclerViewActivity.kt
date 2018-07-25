package com.allen.recyclerviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Button
import com.allen.recyclerviewdemo.adapter.RecyclerViewAdapter

class RecyclerViewActivity : AppCompatActivity(), View.OnClickListener {
    private var mBtnAdd: Button? = null
    private var mBtnRemove: Button? = null
    private var mBtnListView: Button? = null
    private var mBtnGridView: Button? = null
    private var mBtnWaterFallFlow: Button? = null
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

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_add -> addItem()
            R.id.btn_remove -> removeItem()
            R.id.btn_list_view -> showListView()
            R.id.btn_grid_view -> showGridView()
            R.id.btn_water_fall_flow -> showWaterFallFlow()
        }

    }

    private fun addItem() {
        mAdapter?.addItem()
    }

    private fun removeItem() {
        mAdapter?.removeItem()
    }

    private fun showListView() {
        val layoutManager = LinearLayoutManager(this)
        //设置布局管理器
        mRecyclerView?.layoutManager = layoutManager
        //设置item增加和删除时的动画
        mRecyclerView?.itemAnimator = DefaultItemAnimator()
        //设置分割线
        mRecyclerView?.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        //设置适配器
        mAdapter = RecyclerViewAdapter(mData!!, this)
        mRecyclerView?.adapter = mAdapter
    }

    private fun showGridView() {
        val layoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        //设置布局管理器
        mRecyclerView?.layoutManager = layoutManager
        //设置item增加和删除时的动画
        mRecyclerView?.itemAnimator = DefaultItemAnimator()
        //设置分割线
        mRecyclerView?.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        //设置适配器
        mAdapter = RecyclerViewAdapter(mData!!, this)
        mRecyclerView?.adapter = mAdapter
    }


    private fun showWaterFallFlow() {

    }

    private fun initListener() {
        mBtnAdd?.setOnClickListener(this)
        mBtnRemove?.setOnClickListener(this)
        mBtnListView?.setOnClickListener(this)
        mBtnGridView?.setOnClickListener(this)
        mBtnWaterFallFlow?.setOnClickListener(this)

    }

    private fun initData() {
        mData = ArrayList()
        for (i in 'A'..'z') {
            mData?.add("$i")
        }
    }


    private fun useRecyclerView() {

    }

    private fun initView() {
        mBtnAdd = findViewById(R.id.btn_add)
        mBtnRemove = findViewById(R.id.btn_remove)
        mBtnListView = findViewById(R.id.btn_list_view)
        mBtnGridView = findViewById(R.id.btn_grid_view)
        mBtnWaterFallFlow = findViewById(R.id.btn_water_fall_flow)
        mRecyclerView = findViewById(R.id.recycler_view)
    }
}
