package com.jramilo.soundrecorder.model.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by jacobramilo on 11/11/17.
 */
public class MainPagerAdapter: FragmentStatePagerAdapter {
    private var mFragments: ArrayList<Fragment>
    private var mFragmentsName: ArrayList<String>

    init {
        mFragments = ArrayList()
        mFragmentsName = ArrayList()
    }

    constructor(fm: FragmentManager?) : super(fm)

    fun addFragment(name: String, fragment: Fragment) {
        mFragments.add(fragment)
        mFragmentsName.add(name)
    }

    override fun getItem(position: Int): Fragment? {
        return mFragments.get(position)
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentsName.get(position)
    }
}