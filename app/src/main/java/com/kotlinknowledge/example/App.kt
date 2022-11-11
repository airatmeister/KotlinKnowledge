package com.kotlinknowledge.example

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.kotlinknowledge.core.sl.DependencyContainer
import com.kotlinknowledge.core.sl.ViewModelFactory
import com.kotlinknowledge.example.sl.dependencies.FeaturesDependencyContainer
import com.kotlinknowledge.example.sl.dependencies.MainDependencyContainer
import com.kotlinknowledge.example.sl.modules.CoreModule

class App : Application(),
    com.kotlinknowledge.core.sl.ViewModelProvider {

    private val coreModule = CoreModule()

    private lateinit var viewModelsFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
        val main = MainDependencyContainer(DependencyContainer.Error(), coreModule)
        viewModelsFactory = ViewModelFactory(FeaturesDependencyContainer(coreModule, main))
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}