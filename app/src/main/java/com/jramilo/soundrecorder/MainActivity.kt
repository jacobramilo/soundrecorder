package com.jramilo.soundrecorder

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.jramilo.soundrecorder.widget.RecordButtonListener
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.widget.AdapterView
import com.jramilo.soundrecorder.adapter.RecordingsBaseAdapter
import com.jramilo.soundrecorder.widget.PlaybackDialog

class MainActivity : AppCompatActivity(), RecordButtonListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private val permissions = arrayOf<String>(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200
    private var permissionGranted:Boolean = false;
    var recordingsBaseAdapter: RecordingsBaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(app_bar as Toolbar);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        buttonRecord.setOnClickListener(this)
        buttonRecord.recordingListener = this

        reloadRecordingList()
    }

    fun reloadRecordingList() {
        recordingsBaseAdapter = RecordingsBaseAdapter(this)
        var recordings: ArrayList<String> = ArrayList()
        recordingsListView.adapter = recordingsBaseAdapter
        recordingsListView.setOnItemClickListener(this)
        recordingsBaseAdapter?.reloadRecordings()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true
                }
            }
        }
    }

    override fun onStopRecording() {
        chronometer.visibility = View.GONE
        chronometer.stop()
        tvRecordingState.text = getString(R.string.tap_btn_to_start)
        stopService(Intent(this, RecordingService::class.java))

        recordingsBaseAdapter?.reloadRecordings()
    }

    override fun onStartRecording() {
        chronometer.visibility = View.VISIBLE
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        tvRecordingState.text = getString(R.string.recording)
        startService(Intent(this, RecordingService::class.java));
    }

    override fun onClick(view: View?) {
        when(view) {
            buttonRecord -> {
                if(permissionGranted) {
                    buttonRecord.toggle();
                } else {
                    Snackbar.make(mainConstraintLayout, getString(R.string.error_permission), Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        var playbackDialog: PlaybackDialog = PlaybackDialog.newInstance(adapterView?.getItemAtPosition(position) as String)
        playbackDialog.show(supportFragmentManager, "My Dialog")
    }
}
