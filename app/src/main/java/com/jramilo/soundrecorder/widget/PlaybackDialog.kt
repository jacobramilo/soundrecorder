package com.jramilo.soundrecorder.widget

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import com.jramilo.soundrecorder.R

/**
 * Created by jacobramilo on 7/11/17.
 */
class PlaybackDialog : DialogFragment() {
    private var filename:String? = null

    companion object {
        private var FILENAME_KEY: String = "filename"

        fun newInstance(filename: String): PlaybackDialog {
            var pd: PlaybackDialog = PlaybackDialog()
            var bundle: Bundle = Bundle()
            bundle.putString(FILENAME_KEY, filename)
            pd.arguments = bundle

            return pd
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("PlaybackDialog", ">>>>>>>>>>>>>>>>>>>" + arguments)

        filename = arguments.getString(FILENAME_KEY)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        var view: View = activity.layoutInflater.inflate(R.layout.playback_layout, null, false)

        var tvRecording: TextView = view.findViewById(R.id.tvRecord)
        tvRecording.text = filename

        var tvPlaytime: TextView = view.findViewById(R.id.tvPlayTime)
        var tvMaxPlaytime: TextView = view.findViewById(R.id.tvMaxPlayTime)

        var btnPlay: FloatingActionButton = view.findViewById(R.id.fabPlay)
        btnPlay.setOnClickListener(View.OnClickListener {

        })

        builder.setView(view)
        var dialog:Dialog = builder.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        return dialog
    }
}