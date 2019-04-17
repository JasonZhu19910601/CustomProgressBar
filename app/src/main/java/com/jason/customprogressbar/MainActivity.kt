package com.jason.customprogressbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val MSG_UPDATE = 0x110;
    private val mHandler =
        @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                var progress = cpb.progress
                cpb.progress = ++progress
                rpwp.progress = ++progress
                if (progress >= 100) {
                    msg?.target?.removeMessages(MSG_UPDATE)
                }
                msg?.target?.sendEmptyMessageDelayed(MSG_UPDATE, 100)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHandler.sendEmptyMessage(MSG_UPDATE)
    }
}
