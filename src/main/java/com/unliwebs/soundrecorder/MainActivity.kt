package com.unliwebs.soundrecorder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.widget.Toolbar
import android.view.View
import com.unliwebs.soundrecorder.widget.RecordButtonListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecordButtonListener, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar);

        buttonRecord.setOnClickListener(this)
        buttonRecord.recordingListener = this
    }

    override fun onStopRecording() {
        chronometer.stop()
        tvRecordingState.text = getString(R.string.tap_btn_to_start)
    }

    override fun onStartRecording() {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        tvRecordingState.text = getString(R.string.recording)
    }

    override fun onClick(view: View?) {
        when(view) {
            buttonRecord -> {
                buttonRecord.toggle();
            }
        }
    }
}
