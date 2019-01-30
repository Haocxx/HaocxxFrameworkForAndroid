package com.haocxx.test.badge

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import com.haocxx.framework.util.system.NotificationBadgeUtil
import com.haocxx.haocxxframework.R
import kotlinx.android.synthetic.main.activity_badge_test.*

/**
 * Created by Haocxx
 * on 2019/1/30
 */
class BadgeTestActivity : AppCompatActivity() {
    private val _channelId = "my_channel_01"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge_test)
        initNotificationChannel()

        show_badge_notification_btn1.setOnClickListener {
            clickShowBadge(1)
        }

        show_badge_notification_btn2.setOnClickListener {
            clickShowBadge(2)
        }
    }

    private fun initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(_channelId, packageName, NotificationManager.IMPORTANCE_LOW).apply {
                description = ""
                setShowBadge(true)
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun clickShowBadge(count : Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationCompat.Builder(this@BadgeTestActivity, _channelId)
                    .setContentTitle("New Messages")
                    .setContentText("You've received 3 new messages.")
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setNumber(count)
                    .build()
            val notifyMgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notifyMgr.notify(0, notification)
        } else {
            val notification = NotificationCompat.Builder(this@BadgeTestActivity, _channelId)
                    .setContentTitle("New Messages")
                    .setContentText("You've received 3 new messages.")
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .build()
            NotificationBadgeUtil.showBadge(this, notification, 0, count)
        }
    }
}