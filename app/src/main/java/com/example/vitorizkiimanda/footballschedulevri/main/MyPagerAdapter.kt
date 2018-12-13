package com.example.vitorizkiimanda.footballschedulevri.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vitorizkiimanda.footballschedulevri.main.fragments.FavouriteFragment
import com.example.vitorizkiimanda.footballschedulevri.main.fragments.LastMatchFragment
import com.example.vitorizkiimanda.footballschedulevri.main.fragments.NextMatchFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LastMatchFragment()
            }
            1 -> {
                NextMatchFragment()
            }
            else -> {
                return FavouriteFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            else -> {
                return "Favourites"
            }
        }
    }

}