package com.kotlinknowledge.example.sl.dependencies

import androidx.lifecycle.ViewModel
import com.kotlinknowledge.core.sl.DependencyContainer
import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.example.presentation.MainActivityViewModel
import com.kotlinknowledge.example.sl.modules.CoreModule
import com.kotlinknowledge.example.sl.modules.MainModule

class MainDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<*> =
        if (clazz == MainActivityViewModel::class.java)
            MainModule(coreModule)
        else
            dependencyContainer.module(clazz)
}