package com.jramilo.soundrecorder.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.jramilo.soundrecorder.R
import com.jramilo.soundrecorder.adapter.RecordingsBaseAdapter
import com.jramilo.soundrecorder.widget.PlaybackDialog

/**
 * Created by jacobramilo on 10/11/17.
 */
class RecordingListFragment: Fragment(), AdapterView.OnItemClickListener  {
    var recordingsBaseAdapter: RecordingsBaseAdapter? = null
    var recordingsListView: ListView? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater?.inflate(R.layout.my_recordings_layout, container, false)

        recordingsListView = view?.findViewById<ListView>(R.id.recordingsListView)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reloadRecordingList()
    }

    fun reloadRecordingList() {
        recordingsBaseAdapter = RecordingsBaseAdapter(activity)
        var recordings: ArrayList<String> = ArrayList()
        recordingsListView?.adapter = recordingsBaseAdapter
        recordingsListView?.setOnItemClickListener(this)
        recordingsBaseAdapter?.reloadRecordings()
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        var playbackDialog: PlaybackDialog = PlaybackDialog.newInstance(adapterView?.getItemAtPosition(position) as String)
        playbackDialog.show(activity.supportFragmentManager, "My Dialog")
    }
}