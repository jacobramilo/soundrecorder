package com.jramilo.soundrecorder

import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.Environment
import android.os.IBinder
import android.util.Log
import java.io.File

/**
 * Created by jacobramilo on 7/11/17.
 */
class RecordingService: Service() {
    private var mediaRecorder: MediaRecorder? = null;

    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)

        var recordingsDir:File = File(Constant.RECORDINGS_DIRECTORY)
        if(!recordingsDir.exists())
            recordingsDir.mkdir()

        mediaRecorder?.setOutputFile(recordingsDir.absolutePath + "/recording_" + System.currentTimeMillis() + ".mp4")
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder?.prepare()
        mediaRecorder?.start()

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaRecorder?.stop()
    }
}