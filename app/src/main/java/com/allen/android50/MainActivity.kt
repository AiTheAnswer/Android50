package com.allen.android50

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.allen.cardview.CardViewActivity
import com.allen.recyclerviewdemo.RecyclerViewActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mBtnRecyclerView: Button? = null
    private var mBtnCardView: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.recycler_view -> toRecyclerView()
            R.id.card_view -> toCardView()
        }
    }

    private fun toCardView() {
        startActivity(Intent(this, CardViewActivity::class.java))
    }

    private fun toRecyclerView() {
        startActivity(Intent(this, RecyclerViewActivity::class.java))
    }

    private fun initListener() {
        mBtnRecyclerView?.setOnClickListener(this)
        mBtnCardView?.setOnClickListener(this)
    }

    private fun initView() {
        mBtnRecyclerView = findViewById(R.id.recycler_view)
        mBtnCardView = findViewById(R.id.card_view)
    }
}
