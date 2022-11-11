package com.kotlinknowledge.test.sl

import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.test.presentation.screens.end.EndViewModel

class TestEndModule : BaseModule<EndViewModel> {

    override fun viewModel() = EndViewModel()
}