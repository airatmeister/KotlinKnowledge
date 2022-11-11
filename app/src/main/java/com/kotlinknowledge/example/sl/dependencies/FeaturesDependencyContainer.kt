package com.kotlinknowledge.example.sl.dependencies

import androidx.lifecycle.ViewModel
import com.kotlinknowledge.core.sl.DependencyContainer
import com.kotlinknowledge.core.sl.base.BaseModule
import com.kotlinknowledge.example.sl.modules.CoreModule
import com.kotlinknowledge.main.sl.MainScreenModuleProvider
import com.kotlinknowledge.test.sl.TestModuleProvider

class FeaturesDependencyContainer(
    coreModule: CoreModule,
    private val dependencyContainer: DependencyContainer
) : DependencyContainer {

    private val mainScreenModuleProvider = MainScreenModuleProvider(dependencyContainer)
    private val testModuleProvider = TestModuleProvider(dependencyContainer)

    override fun <T : ViewModel> module(clazz: Class<T>): BaseModule<*> =
        when (clazz) {
            in mainScreenModuleProvider.getArrayOfViewModels() -> mainScreenModuleProvider.provide(
                clazz
            )
            in testModuleProvider.getArrayOfViewModels() -> testModuleProvider.provide(clazz)
            else -> dependencyContainer.module(clazz)
        }
}