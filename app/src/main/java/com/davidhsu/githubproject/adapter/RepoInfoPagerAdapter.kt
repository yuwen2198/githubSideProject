package com.davidhsu.githubproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.davidhsu.githubproject.ui.fragment.CollaboratorsFragment
import com.davidhsu.githubproject.ui.fragment.CommitListFragment

class RepoInfoPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragments: MutableList<Fragment> = ArrayList()

    init {
        fragments.add(CommitListFragment())
        fragments.add(CollaboratorsFragment())
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}