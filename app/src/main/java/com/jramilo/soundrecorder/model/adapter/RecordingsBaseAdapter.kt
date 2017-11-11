package com.jramilo.soundrecorder.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.jramilo.soundrecorder.R
import java.io.File

/**
 * Created by jacobramilo on 7/11/17.
 */
 class RecordingsBaseAdapter(context: Context): BaseAdapter() {
    private var recordings: ArrayList<String> = ArrayList()
    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    fun addToList(files: Array<File>?) {
        recordings.clear()
        files?.forEach {
            recordings.add(it.name)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if(convertView == null) {
            view = mInflator.inflate(R.layout.recordings_listview_row, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.tvRecording?.text = recordings[position]

        return view
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): String? {
        return recordings[position]
    }

    override fun getCount(): Int {
        return recordings.size
    }

    class ListRowHolder(row: View?) {
        val tvRecording: TextView? = row?.findViewById(R.id.tvRecording)
        val buttonPlay: ImageButton? = row?.findViewById(R.id.buttonPlay)
    }
}