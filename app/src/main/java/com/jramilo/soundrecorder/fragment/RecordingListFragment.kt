package com.jramilo.soundrecorder.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.jramilo.soundrecorder.PlaybackDialog
import com.jramilo.soundrecorder.R
import com.jramilo.soundrecorder.`interface`.RecordingsList
import com.jramilo.soundrecorder.model.DefaultRecordsList
import com.jramilo.soundrecorder.model.adapter.RecordingsBaseAdapter
import kotlinx.android.synthetic.main.my_recordings_layout.*
import java.io.File

/**
 * Created by jacobramilo on 10/11/17.
 */
class RecordingListFragment: Fragment(), AdapterView.OnItemClickListener, RecordingsList.View  {
    private var recordingsBaseAdapter: RecordingsBaseAdapter? = null
    private var listRecordPresenter: RecordingsList.Presenter = DefaultRecordsList(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.my_recordings_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recordingsBaseAdapter = RecordingsBaseAdapter(activity)
        recordingsListView.adapter = recordingsBaseAdapter
        recordingsListView.onItemClickListener = this

        listRecordPresenter.reloadList()
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        var playbackDialog: PlaybackDialog = PlaybackDialog.newInstance(adapterView?.getItemAtPosition(position) as String)
        playbackDialog.show(activity.supportFragmentManager, "My Dialog")
    }

    override fun reloadList(files: Array<File>?) {
        recordingsBaseAdapter?.addToList(files)
        recordingsBaseAdapter?.notifyDataSetChanged()
    }
}