package com.kotlinknowledge.test.presentation.screens.start

import android.os.Bundle
import com.kotlinknowledge.core.plugins.viewbinding.viewBinding
import com.kotlinknowledge.core.presentation.base.BaseFragment
import com.kotlinknowledge.test.R
import com.kotlinknowledge.test.databinding.FragmentStartBinding
import com.kotlinknowledge.test.presentation.TestScreenFragmentContainer

class StartFragment: BaseFragment<StartViewModel>(R.layout.fragment_start) {

    companion object {
        fun newInstance() = StartFragment()
    }

    private val binding by viewBinding(FragmentStartBinding::bind)
    override fun viewModelClass() = StartViewModel::class.java

    override fun callOperations() = Unit

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        buttonStartScreenNext.setOnClickListener {
            (parentFragment as TestScreenFragmentContainer).navigateTo(1)
        }
    }

    override fun onBindViewModel() = Unit
}