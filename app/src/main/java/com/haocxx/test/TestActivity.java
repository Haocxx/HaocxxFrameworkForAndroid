package com.haocxx.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haocxx.framework.haocxxaspect.annotation.RunDurationSign;
import com.haocxx.haocxxframework.R;

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
