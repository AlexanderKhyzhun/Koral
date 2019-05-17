package com.yotalabs.koral.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yotalabs.koral.ui.calendar.CalendarFragment
import com.yotalabs.koral.ui.favourites.FavouritesFragment
import com.yotalabs.koral.ui.notifications.NotificationFragment
import com.yotalabs.koral.ui.profile.ProfileFragment
import com.yotalabs.koral.ui.search.SearchFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = listOf<Fragment>(
        SearchFragment(),
        FavouritesFragment(),
        CalendarFragment(),
        NotificationFragment(),
        ProfileFragment()
    )


    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

}