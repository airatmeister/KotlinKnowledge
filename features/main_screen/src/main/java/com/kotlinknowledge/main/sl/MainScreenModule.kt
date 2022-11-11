package com.kotlinknowledge.main.sl

import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.main.presentation.MainScreenViewModel

class MainScreenModule : BaseModule<MainScreenViewModel> {

    override fun viewModel() = MainScreenViewModel()
}