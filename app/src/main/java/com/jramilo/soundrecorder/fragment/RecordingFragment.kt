package com.jramilo.soundrecorder.fragment

import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jramilo.soundrecorder.R
import com.jramilo.soundrecorder.model.DefaultRecorder
import com.jramilo.soundrecorder.presenter.IRecorderPresenter
import com.jramilo.soundrecorder.view.IRecorderView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recording_layout.*

/**
 * Created by jacobramilo on 10/11/17.
 */
class RecordingFragment: Fragment(), View.OnClickListener, IRecorderView {
    var recorderPresenter: IRecorderPresenter? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.recording_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recorderPresenter = DefaultRecorder(this)
        buttonRecord.setOnClickListener(this)
    }

    override fun onStopped() {
        chronometer.stop()
        tvRecordingState.text = getString(R.string.tap_btn_to_start)
        buttonRecord.setImageResource(R.drawable.ic_start_recording)
    }

    override fun onStarted() {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        tvRecordingState.text = getString(R.string.recording)
        buttonRecord.setImageResource(R.drawable.ic_stop_recording)
    }

    override fun onError(message: String) {
        Snackbar.make(mainConstraintLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onClick(view: View?) {
        when(view) {
            buttonRecord -> {
                recorderPresenter?.record()
            }
        }
    }
}