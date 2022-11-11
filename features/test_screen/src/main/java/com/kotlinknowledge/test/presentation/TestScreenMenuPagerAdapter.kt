package com.kotlinknowledge.test.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kotlinknowledge.test.presentation.screens.end.EndFragment
import com.kotlinknowledge.test.presentation.screens.start.StartFragment
import com.kotlinknowledge.test.presentation.screens.test.TestFragment

class TestScreenMenuPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StartFragment.newInstance()
            1 -> TestFragment.newInstance()
            2 -> EndFragment.newInstance()
            else -> StartFragment.newInstance()
        }

    }
}