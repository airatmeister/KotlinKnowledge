package com.kotlinknowledge.core.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.kotlinknowledge.core.plugins.BasePlugin
import com.kotlinknowledge.core.plugins.LifecycleEvent
import com.kotlinknowledge.core.presentation.Navigator
import com.kotlinknowledge.core.sl.ViewModelProvider

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity(), ViewModelProvider, Navigator {

    protected lateinit var viewModel: VM

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ViewModelProvider).provideViewModel(clazz, owner)

    private val plugins = mutableListOf<BasePlugin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        initPlugins()
        dispatchEventToPlugins(LifecycleEvent.BeforeOnCreate(savedInstanceState))
        super.onCreate(savedInstanceState)
        dispatchEventToPlugins(LifecycleEvent.OnCreate(savedInstanceState))
        onBindViewModel()
    }

    abstract fun onBindViewModel()

    abstract fun onSetupLayout()

    // region Plugins

    @CallSuper
    protected open fun initPlugins() = Unit

    protected fun addPlugin(plugin: BasePlugin) {
        plugins.add(plugin)
    }

    private fun dispatchEventToPlugins(event: LifecycleEvent) {
        plugins.forEach { it.onLifecycleEvent(event) }
    }

    private fun releasePlugins() {
        plugins.clear()
    }

    // endregion

    override fun onResume() {
        dispatchEventToPlugins(LifecycleEvent.OnResume)
        super.onResume()
    }

    override fun onPause() {
        dispatchEventToPlugins(LifecycleEvent.OnPause)
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        dispatchEventToPlugins(LifecycleEvent.OnStart)
        onSetupLayout()
    }

    override fun onDestroy() {
        dispatchEventToPlugins(LifecycleEvent.OnDestroy)
        releasePlugins()
        super.onDestroy()
    }

    protected abstract val container: Int

    protected fun showFragment(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        animation: (FragmentTransaction) -> Unit = {}
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        animation(transaction)
        transaction.replace(container, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

}