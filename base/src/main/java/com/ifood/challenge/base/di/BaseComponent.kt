package com.ifood.challenge.base.di

import androidx.lifecycle.ViewModelProvider

/**
 * Function to get the current [BaseComponent] instance
 */
fun base() = BaseComponent.INSTANCE

/**
 * Base component to all Dagger components
 */
interface BaseComponent {

    /**
     * The [ViewModelProvider.Factory] injected by Dagger
     */
    val viewModelFactory: ViewModelProvider.Factory

    companion object {
        lateinit var INSTANCE: BaseComponent
    }
}
