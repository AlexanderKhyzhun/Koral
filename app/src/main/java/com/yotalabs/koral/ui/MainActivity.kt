package com.yotalabs.koral.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.yotalabs.koral.R
import com.yotalabs.koral.ui.adapters.MainPagerAdapter
import com.yotalabs.koral.ui.calendar.CalendarFragment
import com.yotalabs.koral.ui.favourites.FavouritesFragment
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.ui.notifications.NotificationFragment
import com.yotalabs.koral.ui.profile.ProfileFragment
import com.yotalabs.koral.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNavigation()
        setUpViewPager()
        //setTranslucentStatusBar()
    }

    private fun setUpViewPager() {
        activity_main_pager.offscreenPageLimit = OFFSCREEN_PAGE_LIMIT
        activity_main_pager.adapter = MainPagerAdapter(supportFragmentManager)
        activity_main_navigation.selectedItemId = R.id.navigation_profile
        activity_main_pager.addOnPageChangeListener(this)
    }

    private fun setUpBottomNavigation() {
        activity_main_navigation.setOnNavigationItemSelectedListener(this)
        activity_main_navigation.selectedItemId = R.id.navigation_profile
        activity_main_navigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        when (position) {
            SearchFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_search
            }
            FavouritesFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_favourites
            }
            CalendarFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_calendar
            }
            NotificationFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_notifications
            }
            ProfileFragment.PAGER_POSITION -> {
                activity_main_navigation.selectedItemId = R.id.navigation_profile
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_search -> {
                activity_main_pager.currentItem = SearchFragment.PAGER_POSITION
            }
            R.id.navigation_favourites -> {
                activity_main_pager.currentItem = FavouritesFragment.PAGER_POSITION
            }
            R.id.navigation_calendar -> {
                activity_main_pager.currentItem = CalendarFragment.PAGER_POSITION
            }
            R.id.navigation_notifications -> {
                activity_main_pager.currentItem = NotificationFragment.PAGER_POSITION
            }
            R.id.navigation_profile -> {
                activity_main_pager.currentItem = ProfileFragment.PAGER_POSITION
            }
        }
        return true
    }


    companion object {
        private const val OFFSCREEN_PAGE_LIMIT = 5
        const val TAG = "MainActivity"
        const val colorTransparent = "#FFFFFFFF"
        fun getIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }


}
