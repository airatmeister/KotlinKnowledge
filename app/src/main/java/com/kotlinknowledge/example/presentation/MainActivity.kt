package com.kotlinknowledge.example.presentation

import android.os.Bundle
import com.kotlinknowledge.core.plugins.viewbinding.viewBinding
import com.kotlinknowledge.core.presentation.base.BaseActivity
import com.kotlinknowledge.example.R
import com.kotlinknowledge.example.databinding.ActivityMainBinding
import com.kotlinknowledge.main.presentation.MainScreenFragment
import com.kotlinknowledge.test.presentation.TestScreenFragmentContainer

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override val container = R.id.container

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun navigateTo(featureName: String) {
        if (featureName == "test") showFragment(TestScreenFragmentContainer.newInstance())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            showFragment(MainScreenFragment.newInstance())
        }
    }

    override fun onBindViewModel() = Unit

    override fun onSetupLayout() = Unit
}