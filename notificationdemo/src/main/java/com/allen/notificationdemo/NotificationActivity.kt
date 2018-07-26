package com.allen.notificationdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RemoteViews

class NotificationActivity : AppCompatActivity(), View.OnClickListener {
    private var mBtnCommonNotification: Button? = null
    private var mBtnFoldNotification: Button? = null
    private var mBtnHangNotification: Button? = null
    private var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        supportActionBar?.hide()
        initView()
        initData()
        initListener()
    }

    private fun initData() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_common_notification -> showCommonNotification()
            R.id.btn_fold_notification -> showFoldNotification()
            R.id.btn_hang_notification -> showHangNotification()
        }

    }

    /**
     * 显示一个普通的Notification
     */
    private fun showCommonNotification() {
        //创建一个Notification
        //使用Builder构建者模式
        val builder = Notification.Builder(this)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"))
        val pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(R.drawable.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setAutoCancel(true)
        builder.setContentTitle("普通通知")
        val notification = builder.build()
        notificationManager?.notify(0, notification)
    }

    /**
     * 显示一个折叠式的Notification
     */
    private fun showFoldNotification() {
        //因为这个视图的显示进程和我们创建视图的进程不在同一个进程，所以需要使用RemoteViews
        //创建Notification
        val builder = Notification.Builder(this)
        builder.setContentTitle("折叠式的Notification")
        builder.setSmallIcon(R.drawable.ic_launcher)
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"))
        val pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(true)
        builder.setContentText("折叠式Notification的内容")
        //使用RemoteViews来创建自定义Notification视图
        val remoteViews = RemoteViews(packageName, R.layout.view_fold)
        //指定打开时的视图
        val notification = builder.build()
        notification.bigContentView = remoteViews
        notificationManager?.notify(1, notification)
    }

    /**
     * 显示一个悬挂式的Notification
     */
    private fun showHangNotification() {
        val builder = Notification.Builder(this)
        builder.setContentText("悬挂式Notification的内容")
        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"))
        val pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setContentTitle("悬挂式Notification")
        builder.setSmallIcon(R.drawable.ic_launcher)
        builder.setAutoCancel(true)
        //设置点击跳转
        val hangIntent = Intent()
        hangIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        hangIntent.setClass(this, NotificationActivity::class.java)
        val hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setFullScreenIntent(hangPendingIntent, true)
        val notification = builder.build()
        notificationManager?.notify(2, notification)
    }

    private fun initListener() {
        mBtnCommonNotification?.setOnClickListener(this)
        mBtnFoldNotification?.setOnClickListener(this)
        mBtnHangNotification?.setOnClickListener(this)

    }


    private fun initView() {
        mBtnCommonNotification = findViewById(R.id.btn_common_notification)
        mBtnFoldNotification = findViewById(R.id.btn_fold_notification)
        mBtnHangNotification = findViewById(R.id.btn_hang_notification)
    }
}
