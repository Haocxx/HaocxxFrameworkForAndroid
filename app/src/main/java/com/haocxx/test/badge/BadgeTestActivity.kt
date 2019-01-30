package com.haocxx.test.badge

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import com.haocxx.haocxxframework.R
import kotlinx.android.synthetic.main.activity_badge_test.*

/**
 * Created by Haocxx
 * on 2019/1/30
 */
class BadgeTestActivity : AppCompatActivity() {
    private val _channelId = "my_channel_01"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_badge_test)

        show_badge_notification_btn.setOnClickListener {

        }
    }

    private fun clickShowBadge(count : Int) {

        var notification = NotificationCompat.Builder(this@BadgeTestActivity, _channelId)
                .setContentTitle("New Messages")
                .setContentText("You've received 3 new messages.")
                .setNumber(count)
                .build()
    }
}