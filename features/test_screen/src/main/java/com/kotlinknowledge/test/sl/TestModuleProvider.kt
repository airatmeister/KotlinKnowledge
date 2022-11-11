package com.kotlinknowledge.test.sl

import androidx.lifecycle.ViewModel
import com.kotlinknowledge.core.sl.DependencyContainer
import com.kotlinknowledge.test.presentation.TestScreenContainerViewModel
import com.kotlinknowledge.test.presentation.screens.end.EndViewModel
import com.kotlinknowledge.test.presentation.screens.start.StartViewModel
import com.kotlinknowledge.test.presentation.screens.test.TestViewModel

class TestModuleProvider(
    private val dependencyContainer: DependencyContainer
) {
    fun <T : ViewModel> provide(clazz: Class<T>) = when (clazz) {
        TestScreenContainerViewModel::class.java -> TestContainerModule()
        StartViewModel::class.java -> TestStartModule()
        TestViewModel::class.java -> TestTestModule()
        EndViewModel::class.java -> TestEndModule()
        else -> dependencyContainer.module(clazz)
    }

    fun getArrayOfViewModels(): Array<Class<*>> =
        arrayOf(
            TestScreenContainerViewModel::class.java,
            StartViewModel::class.java,
            TestViewModel::class.java,
            EndViewModel::class.java
        )
}