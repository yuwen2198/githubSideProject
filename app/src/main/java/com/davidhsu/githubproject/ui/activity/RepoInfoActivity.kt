package com.davidhsu.githubproject.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.adapter.RepoInfoPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_repo_info.*

class RepoInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_info)

        initTab()
    }

    private fun initTab() {
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("commit list"))
            addTab(tabLayout.newTab().setText("collaborators list"))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewpager.currentItem = tab.position
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }

        viewpager.adapter = RepoInfoPagerAdapter(supportFragmentManager)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }

}