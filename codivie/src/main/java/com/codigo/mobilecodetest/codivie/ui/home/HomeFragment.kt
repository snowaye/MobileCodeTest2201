package com.codigo.mobilecodetest.codivie.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.codigo.mobilecodetest.codivie.R
import com.codigo.mobilecodetest.codivie.adapters.*
import com.codigo.mobilecodetest.codivie.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MOVIES_PAGE_INDEX -> getString(R.string.tab_movies_title)
            EVENTS_PAGE_INDEX -> getString(R.string.tab_events_title)
            PLAYS_PAGE_INDEX -> getString(R.string.tab_plays_title)
            SPORTS_PAGE_INDEX -> getString(R.string.tab_sports_title)
            ACTIVITIES_PAGE_INDEX -> getString(R.string.tab_activities_title)
            else -> null
        }
    }

}