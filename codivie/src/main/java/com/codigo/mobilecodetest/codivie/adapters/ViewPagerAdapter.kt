package com.codigo.mobilecodetest.codivie.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codigo.mobilecodetest.codivie.ui.activities.ActivitiesFragment
import com.codigo.mobilecodetest.codivie.ui.events.EventsFragment
import com.codigo.mobilecodetest.codivie.ui.movies.MoviesFragment
import com.codigo.mobilecodetest.codivie.ui.plays.PlaysFragment
import com.codigo.mobilecodetest.codivie.ui.sports.SportsFragment

const val MOVIES_PAGE_INDEX = 0
const val EVENTS_PAGE_INDEX = 1
const val PLAYS_PAGE_INDEX = 2
const val SPORTS_PAGE_INDEX = 3
const val ACTIVITIES_PAGE_INDEX = 4


class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MOVIES_PAGE_INDEX to { MoviesFragment() },
        EVENTS_PAGE_INDEX to { EventsFragment() },
        PLAYS_PAGE_INDEX to { PlaysFragment() },
        SPORTS_PAGE_INDEX to { SportsFragment() },
        ACTIVITIES_PAGE_INDEX to { ActivitiesFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}
