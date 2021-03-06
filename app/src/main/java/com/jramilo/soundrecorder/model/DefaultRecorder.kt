package com.jramilo.soundrecorder.model

import android.media.MediaRecorder
import com.jramilo.soundrecorder.`interface`.MainRecorder
import com.jramilo.soundrecorder.model.util.AppPermission
import com.jramilo.soundrecorder.model.util.Constant
import java.io.File

/**
 * Created by jacobramilo on 11/11/17.
 */
class DefaultRecorder: MainRecorder.Presenter {
    private var recorderView: MainRecorder.View
    private var isRecording: Boolean = false
    private var mediaRecorder: MediaRecorder? = null;

    constructor(recorderView: MainRecorder.View) {
        this.recorderView = recorderView
    }

    override fun record() {
        if(AppPermission.isGranted) {
            if (isRecording) {
                mediaRecorder?.stop()
                mediaRecorder?.release()
                mediaRecorder = null
                recorderView.onStopped()
            } else {
                mediaRecorder = MediaRecorder()
                mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)

                var recordingsDir: File = File(Constant.RECORDINGS_DIRECTORY)
                if(!recordingsDir.exists())
                    recordingsDir.mkdir()

                mediaRecorder?.setOutputFile(recordingsDir.absolutePath + "/recording_" + System.currentTimeMillis() + ".mp4")
                mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                recorderView.onStarted()
            }
            isRecording = !isRecording
        } else {
            recorderView.onError("Please grant the permissions in Android settings!")
        }
    }
}