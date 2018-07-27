package com.allen.android50

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.allen.cardview.CardViewActivity
import com.allen.notificationdemo.NotificationActivity
import com.allen.recyclerviewdemo.RecyclerViewActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mBtnRecyclerView: Button? = null
    private var mBtnCardView: Button? = null
    private var mBtnNotification: Button? = null
    private var mToolbar: Toolbar? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mToolbar?.setOnMenuItemClickListener(object : android.widget.Toolbar.OnMenuItemClickListener, Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.action_settings -> showToast("action_settings")
                    R.id.action_share -> showToast("action_share")
                    R.id.ab_search -> showToast("ab_search")

                }
                return true
            }

        })
        mDrawerToggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close)
        mDrawerToggle?.syncState()
        mDrawerLayout?.addDrawerListener(mDrawerToggle!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.recycler_view -> toRecyclerView()
            R.id.card_view -> toCardView()
            R.id.notification -> toNotification()
        }
    }

    private fun toNotification() {
        startActivity(Intent(this, NotificationActivity::class.java))
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
        mBtnNotification?.setOnClickListener(this)
    }

    private fun initView() {
        mBtnRecyclerView = findViewById(R.id.recycler_view)
        mBtnCardView = findViewById(R.id.card_view)
        mBtnNotification = findViewById(R.id.notification)
        mToolbar = findViewById(R.id.toolbar)
        mDrawerLayout = findViewById(R.id.drawer_layout)
    }
}
