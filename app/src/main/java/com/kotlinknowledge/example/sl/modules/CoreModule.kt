package com.kotlinknowledge.example.sl.modules

import android.content.Context
import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.example.presentation.MainActivityViewModel

class CoreModule: BaseModule<MainActivityViewModel> {

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    override fun viewModel() = MainActivityViewModel()

    fun getApplicationContext() = context
}