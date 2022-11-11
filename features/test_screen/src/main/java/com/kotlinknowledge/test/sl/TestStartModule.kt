package com.kotlinknowledge.test.sl

import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.test.presentation.screens.start.StartViewModel

class TestStartModule : BaseModule<StartViewModel> {

    override fun viewModel() = StartViewModel()
}