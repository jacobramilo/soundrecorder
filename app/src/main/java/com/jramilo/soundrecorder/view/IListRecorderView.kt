package com.jramilo.soundrecorder.view

import java.io.File

/**
 * Created by jacobramilo on 11/11/17.
 */
interface IListRecorderView {
    fun reloadList(files: Array<File>?)
}