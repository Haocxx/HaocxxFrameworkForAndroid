package com.haocxx.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.haocxx.framework.constant.filter.FilterColorMatrixConstant
import com.haocxx.framework.util.media.BitmapUtil
import com.haocxx.framework.util.media.FilterUtil
import com.haocxx.haocxxframework.R
import kotlinx.android.synthetic.main.activity_filter_test.*

class FilterTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_test)
        initView()
    }

    private fun initView() {
        image_view.setImageResource(R.drawable.test_image)
        filter_text_btn.setOnClickListener {
            val bitmap = FilterUtil.getMatrixBitmap(BitmapUtil.getBitmapFromResource(this, R.drawable.test_image),
                    FilterColorMatrixConstant.matrix3)
            image_view.setImageBitmap(bitmap)
        }
    }
}
