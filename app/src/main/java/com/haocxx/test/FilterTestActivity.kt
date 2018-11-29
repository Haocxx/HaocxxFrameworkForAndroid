package com.haocxx.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.haocxx.haocxxframework.R
import kotlinx.android.synthetic.main.activity_filter_test.*

class FilterTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_test)
        initView()
    }

    private fun initView() {
        filter_text_btn.setOnClickListener {

        }
    }
}
