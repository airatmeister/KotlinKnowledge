package com.kotlinknowledge.test.presentation.screens.test

import android.os.Bundle
import com.kotlinknowledge.core.plugins.viewbinding.viewBinding
import com.kotlinknowledge.core.presentation.base.BaseFragment
import com.kotlinknowledge.test.R
import com.kotlinknowledge.test.databinding.FragmentTestBinding
import com.kotlinknowledge.test.presentation.TestScreenFragmentContainer

class TestFragment: BaseFragment<TestViewModel>(R.layout.fragment_test) {

    companion object{
        fun newInstance() = TestFragment()
    }

    private val binding by viewBinding(FragmentTestBinding::bind)

    override fun viewModelClass() = TestViewModel::class.java

    override fun callOperations() = Unit

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        buttonTestNext.setOnClickListener {
            (parentFragment as TestScreenFragmentContainer).navigateTo(2)
        }
    }

    override fun onBindViewModel() = Unit
}