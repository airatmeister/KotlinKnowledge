package com.kotlinknowledge.core.plugins

interface BasePlugin {
    fun onLifecycleEvent(event: LifecycleEvent)
}