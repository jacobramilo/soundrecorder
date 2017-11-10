package com.jramilo.soundrecorder.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jramilo.soundrecorder.R
import com.jramilo.soundrecorder.RecordingService
import com.jramilo.soundrecorder.widget.RecordButtonListener
import kotlinx.android.synthetic.main.recording_layout.*

/**
 * Created by jacobramilo on 10/11/17.
 */
class RecordingFragment: Fragment(), View.OnClickListener, RecordButtonListener {
    private val permissions = arrayOf<String>(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.recording_layout, container, false)
    }

    fun checkPermisions() : Boolean {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonRecord.recordingListener = this
        buttonRecord.setOnClickListener(this)
    }

    override fun onStopRecording() {
        chronometer.stop()
        tvRecordingState.text = getString(R.string.tap_btn_to_start)
        activity.stopService(Intent(activity, RecordingService::class.java))
    }

    override fun onStartRecording() {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        tvRecordingState.text = getString(R.string.recording)
        activity.startService(Intent(activity, RecordingService::class.java));
    }

    override fun onClick(view: View?) {
        when(view) {
            buttonRecord -> {
                if(checkPermisions()) {
                    buttonRecord?.toggle();
                } else {
                    ActivityCompat.requestPermissions(activity, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
                }
            }
        }
    }
}