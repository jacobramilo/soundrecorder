package com.jramilo.soundrecorder.model

import android.util.Log
import com.jramilo.soundrecorder.`interface`.RecordingsList
import com.jramilo.soundrecorder.model.util.Constant
import java.io.File

/**
 * Created by jacobramilo on 11/11/17.
 */
class DefaultRecordsList: RecordingsList.Presenter {
    private var listRecorderView: RecordingsList.View

    constructor(listRecorderView: RecordingsList.View) {
        this.listRecorderView = listRecorderView
    }

    override fun reloadList() {
        var file: File? = File(Constant.RECORDINGS_DIRECTORY)

        Log.d("DefaultRecordsList", ">>>> " + file?.listFiles()?.size)

        listRecorderView.reloadList(file?.listFiles())
    }
}