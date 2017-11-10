package com.jramilo.soundrecorder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.jramilo.soundrecorder.fragment.RecordingFragment
import com.jramilo.soundrecorder.fragment.RecordingListFragment

class MainActivity : AppCompatActivity() {
    private var viewPagerAdapter: MainPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(app_bar as Toolbar);

        viewPagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPagerAdapter?.addFragment("Record", RecordingFragment())
        viewPagerAdapter?.addFragment("Recordings", RecordingListFragment())
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

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
}