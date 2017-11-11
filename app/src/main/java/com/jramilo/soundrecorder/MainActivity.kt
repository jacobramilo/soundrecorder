package com.jramilo.soundrecorder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.jramilo.soundrecorder.model.adapter.MainPagerAdapter
import com.jramilo.soundrecorder.fragment.RecordingFragment
import com.jramilo.soundrecorder.fragment.RecordingListFragment
import com.jramilo.soundrecorder.model.util.AppPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val permissions = arrayOf<String>(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200

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

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    AppPermission.isGranted = true
                }
            }
        }
    }
}