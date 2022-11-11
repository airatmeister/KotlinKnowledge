package com.kotlinknowledge.main.presentation

import android.os.Bundle
import com.kotlinknowledge.core.plugins.viewbinding.viewBinding
import com.kotlinknowledge.core.presentation.base.BaseFragment
import com.kotlinknowledge.main.R
import com.kotlinknowledge.main.databinding.FragmentMainBinding

class MainScreenFragment : BaseFragment<MainScreenViewModel>(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private val binding by viewBinding(FragmentMainBinding::bind)
    override fun viewModelClass() = MainScreenViewModel::class.java

    override fun callOperations() = Unit

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        buttonStartTest.setOnClickListener {
            navigator.navigateTo("test")
        }
    }

    override fun onBindViewModel() = Unit

    override fun onBackPressed() {
        requireActivity().finish()
    }
}