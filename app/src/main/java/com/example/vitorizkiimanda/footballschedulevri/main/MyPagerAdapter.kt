package com.example.vitorizkiimanda.footballschedulevri.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vitorizkiimanda.footballschedulevri.main.FirstFragment
import com.example.vitorizkiimanda.footballschedulevri.main.SecondFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstFragment()
            }
            else -> {
                return SecondFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Last Match"
            else -> {
                return "Next Match"
            }
        }
    }

}