package com.kotlinknowledge.main.sl

import androidx.lifecycle.ViewModel
import com.kotlinknowledge.core.sl.DependencyContainer
import com.kotlinknowledge.main.presentation.MainScreenViewModel

class MainScreenModuleProvider(
    private val dependencyContainer: DependencyContainer
) {
    fun <T : ViewModel> provide(clazz: Class<T>) = when (clazz) {
        MainScreenViewModel::class.java -> MainScreenModule()
        else -> dependencyContainer.module(clazz)
    }

    fun getArrayOfViewModels(): Array<Class<*>> = arrayOf(MainScreenViewModel::class.java)
}