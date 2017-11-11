package com.jramilo.soundrecorder.`interface`

/**
 * Created by jacobramilo on 11/11/17.
 */
interface MainRecorder {
    interface View {
        fun onStopped()
        fun onStarted()
        fun onError(message: String)
    }

    interface Presenter {
        fun record()
    }
}