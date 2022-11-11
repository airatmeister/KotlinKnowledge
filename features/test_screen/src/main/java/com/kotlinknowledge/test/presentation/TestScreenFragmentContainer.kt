package com.kotlinknowledge.test.presentation

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.kotlinknowledge.core.plugins.viewbinding.viewBinding
import com.kotlinknowledge.core.presentation.base.BaseFragment
import com.kotlinknowledge.test.R
import com.kotlinknowledge.test.databinding.FragmentTestContainerBinding

class TestScreenFragmentContainer : BaseFragment<TestScreenContainerViewModel>(R.layout.fragment_test_container) {

    companion object {
        fun newInstance() = TestScreenFragmentContainer()
    }

    private val binding by viewBinding(FragmentTestContainerBinding::bind)
    override fun viewModelClass() = TestScreenContainerViewModel::class.java

    override fun callOperations() = Unit

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        viewPagerTestContainer.offscreenPageLimit = 3
        viewPagerTestContainer.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
            adapter = TestScreenMenuPagerAdapter(this@TestScreenFragmentContainer)
            viewPagerTestContainer.isUserInputEnabled = false
        }
        Unit
    }

    fun navigateTo(item: Int) {
        binding.viewPagerTestContainer.currentItem = item
    }

    override fun onBindViewModel() = Unit
}