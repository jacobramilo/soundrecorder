package com.jramilo.soundrecorder.model

import android.util.Log
import com.jramilo.soundrecorder.model.util.Constant
import com.jramilo.soundrecorder.presenter.IListRecordsPresenter
import com.jramilo.soundrecorder.view.IListRecorderView
import java.io.File

/**
 * Created by jacobramilo on 11/11/17.
 */
class DefaultRecordsList: IListRecordsPresenter {
    private var listRecorderView: IListRecorderView

    constructor(listRecorderView: IListRecorderView) {
        this.listRecorderView = listRecorderView
    }

    override fun reloadList() {
        var file: File? = File(Constant.RECORDINGS_DIRECTORY)

        Log.d("DefaultRecordsList", ">>>> " + file?.listFiles()?.size)

        listRecorderView.reloadList(file?.listFiles())
    }
}