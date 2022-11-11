package com.kotlinknowledge.example.sl.modules

import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.example.presentation.MainActivityViewModel

class MainModule(private val coreModule: CoreModule) : BaseModule<MainActivityViewModel> {

    override fun viewModel() = MainActivityViewModel()
}