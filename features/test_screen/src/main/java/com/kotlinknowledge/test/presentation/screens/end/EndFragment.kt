package com.kotlinknowledge.test.presentation.screens.end

import android.os.Bundle
import com.kotlinknowledge.core.plugins.viewbinding.viewBinding
import com.kotlinknowledge.core.presentation.base.BaseFragment
import com.kotlinknowledge.test.R
import com.kotlinknowledge.test.databinding.FragmentEndBinding

class EndFragment : BaseFragment<EndViewModel>(R.layout.fragment_end) {

    companion object {
        fun newInstance() = EndFragment()
    }

    private val binding by viewBinding(FragmentEndBinding::bind)
    override fun viewModelClass() = EndViewModel::class.java

    override fun callOperations() = Unit

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        buttonEndScreenEnd.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBindViewModel() = Unit
}