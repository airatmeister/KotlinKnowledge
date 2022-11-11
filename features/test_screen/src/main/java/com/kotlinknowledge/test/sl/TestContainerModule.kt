package com.kotlinknowledge.test.sl

import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.test.presentation.TestScreenContainerViewModel

class TestContainerModule : BaseModule<TestScreenContainerViewModel> {

    override fun viewModel() = TestScreenContainerViewModel()
}