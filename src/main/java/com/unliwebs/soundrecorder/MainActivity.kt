package com.unliwebs.soundrecorder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar);

        buttonRecord.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            buttonRecord -> {
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
            }
        }
    }
}
