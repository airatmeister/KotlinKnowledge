package com.kotlinknowledge.test.sl

import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.test.presentation.screens.test.TestViewModel

class TestTestModule : BaseModule<TestViewModel> {

    override fun viewModel() = TestViewModel()
}