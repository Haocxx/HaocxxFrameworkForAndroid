package com.haocxx.test.filter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        for ((index) in FilterColorMatrixConstant.COLOR_MATRIX_LIST.withIndex()) {
            val button = Button(this)
            content_view.addView(button)
            button.text = "Filter$index"
            button.setOnClickListener {
                val bitmap = FilterUtil.getMatrixBitmap(BitmapUtil.getBitmapFromResource(this@FilterTestActivity, R.drawable.test_image),
                        FilterColorMatrixConstant.COLOR_MATRIX_LIST[index])
                image_view.setImageBitmap(bitmap)
            }
        }
    }
}
