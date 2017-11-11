package com.jramilo.soundrecorder.`interface`

import java.io.File

/**
 * Created by jacobramilo on 11/11/17.
 */
interface RecordingsList {
    interface View {
        fun reloadList(files: Array<File>?)
    }

    interface Presenter {
        fun reloadList()
    }
}