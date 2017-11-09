package com.jramilo.soundrecorder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.jramilo.soundrecorder.R
import com.jramilo.soundrecorder.Constant
import kotlinx.android.synthetic.main.recordings_listview_row.view.*
import java.io.File

/**
 * Created by jacobramilo on 7/11/17.
 */
 public class RecordingsBaseAdapter(context: Context): BaseAdapter() {
    private var recordings: ArrayList<String>;
    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(context)
        recordings = ArrayList()
    }

    public fun reloadRecordings() {
        recordings.clear()
        var file: File? = File(Constant.RECORDINGS_DIRECTORY)
        file?.listFiles()?.forEach {
            recordings.add(it.name)
        }
        notifyDataSetChanged()
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

        vh.tvRecording?.text = recordings?.get(position)

        return view
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): String? {
        return recordings.get(position)
    }

    override fun getCount(): Int {
        return recordings.size;
    }

    class ListRowHolder(row: View?) {
        public val tvRecording: TextView?
        public val buttonPlay: ImageButton?

        init {
            tvRecording = row?.findViewById(R.id.tvRecording)
            buttonPlay = row?.findViewById(R.id.buttonPlay)
        }
    }
}