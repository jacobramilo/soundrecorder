package com.unliwebs.soundrecorder.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import com.unliwebs.soundrecorder.R

/**
 * Created by jacobramilo on 7/11/17.
 */
class PlaybackDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        var view: View = activity.layoutInflater.inflate(R.layout.playback_layout, null, false)
        builder.setView(view)
        var dialog:Dialog = builder.create()

        return dialog
    }
}