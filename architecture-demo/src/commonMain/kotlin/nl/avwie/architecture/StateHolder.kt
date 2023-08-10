package nl.avwie.architecture

import kotlinx.coroutines.flow.StateFlow

interface StateHolder<S> {
    val state: StateFlow<S>
}