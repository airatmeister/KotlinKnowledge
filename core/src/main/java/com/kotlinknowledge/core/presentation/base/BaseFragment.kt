package com.kotlinknowledge.core.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlinknowledge.core.plugins.BasePlugin
import com.kotlinknowledge.core.plugins.LifecycleEvent
import com.kotlinknowledge.core.plugins.viewbinding.ViewBindingPlugin
import com.kotlinknowledge.core.presentation.Navigator

abstract class BaseFragment<VM : ViewModel>(@LayoutRes layout: Int) : Fragment(layout) {

    protected lateinit var viewModel: VM

    protected abstract fun viewModelClass(): Class<VM>

    protected lateinit var navigator: Navigator

    protected open fun onBackPressed() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    val bindingPlugin = ViewBindingPlugin()

    private val plugins = mutableListOf<BasePlugin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = (requireActivity() as BaseActivity<*>).provideViewModel(viewModelClass(), this)
        navigator = (requireActivity() as Navigator)
        initPlugins()
        dispatchEventToPlugins(LifecycleEvent.BeforeOnCreate(savedInstanceState))
        super.onCreate(savedInstanceState)
        dispatchEventToPlugins(LifecycleEvent.OnCreate(savedInstanceState))
        callOperations()
    }

    override fun onResume() {
        dispatchEventToPlugins(LifecycleEvent.OnResume)
        super.onResume()
        (requireActivity() as BaseActivity<*>).apply {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed(): Unit = this@BaseFragment.onBackPressed()
                })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatchEventToPlugins(LifecycleEvent.OnViewCreated(view, savedInstanceState))
        onSetupLayout(savedInstanceState)
        onBindViewModel()
    }

    override fun onPause() {
        dispatchEventToPlugins(LifecycleEvent.OnPause)
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        dispatchEventToPlugins(LifecycleEvent.OnStart)
    }

    override fun onStop() {
        dispatchEventToPlugins(LifecycleEvent.OnStop(this))
        super.onStop()
    }

    override fun onDestroyView() {
        dispatchEventToPlugins(LifecycleEvent.OnDestroyView(this))
        super.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        dispatchEventToPlugins(LifecycleEvent.OnActivityResult(requestCode, resultCode, data))
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        dispatchEventToPlugins(LifecycleEvent.OnSaveInstanceState(outState))
    }

    override fun onDestroy() {
        dispatchEventToPlugins(LifecycleEvent.OnDestroy)
        releasePlugins()
        super.onDestroy()
    }

    abstract fun callOperations()

    abstract fun onSetupLayout(savedInstanceState: Bundle?)

    abstract fun onBindViewModel()

    // region Plugins
    @CallSuper
    protected open fun initPlugins() {
        addPlugin(bindingPlugin)
    }

    protected fun addPlugin(plugin: BasePlugin) {
        plugins.add(plugin)
    }

    private fun dispatchEventToPlugins(event: LifecycleEvent) {
        plugins.forEach { it.onLifecycleEvent(event) }
    }

    private fun releasePlugins() {
        plugins.clear()
    }

    // end region Plugins

    protected infix fun <T> LiveData<T>.observe(block: (T) -> Unit) =
        observe(this@BaseFragment.viewLifecycleOwner) { t -> block(t) }
}