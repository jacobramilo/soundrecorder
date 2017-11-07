package com.unliwebs.soundrecorder.widget

import android.content.Context
import android.util.AttributeSet
import android.support.v7.widget.AppCompatImageButton
import com.unliwebs.soundrecorder.R

/**
 * Created by jacobramilo on 4/11/17.
 */
class RecordButton : AppCompatImageButton {
    var isRecording: Boolean = false
    var recordingListener: RecordButtonListener? = null;

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun toggle() {
        isRecording = !isRecording;

        if (isRecording) {
            setImageResource(R.drawable.ic_stop_recording)
            recordingListener?.onStartRecording()
        } else {
            setImageResource(R.drawable.ic_start_recording)
            recordingListener?.onStopRecording()
        }
    }
}