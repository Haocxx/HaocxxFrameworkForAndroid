package com.haocxx.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.haocxx.framework.haocxxaspect.annotation.RunDurationSign;
import com.haocxx.haocxxframework.R;
import com.haocxx.test.filter.FilterTestActivity;

/**
 * Created by Haocxx
 * on 2018/8/21
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doIt();
        findViewById(R.id.filter_text_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, FilterTestActivity.class);
                startActivity(i);
            }
        });
    }

    @RunDurationSign
    void doIt() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
