package com.mpsi.laurent.randomselect

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val count = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SelectFragment()
            1 -> ListFragment()
            else -> SelectFragment()
        }
    }

    override fun getCount(): Int {
        return count
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Select"
            1 -> "List"
            else -> "Select"
        }
    }


}
