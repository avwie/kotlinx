package nl.avwie.architecture

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow

interface ReadViewModel<S> {
    val state: StateFlow<S>
}

interface ViewModel<S> : ReadViewModel<S> {
    val scope: CoroutineScope?

    fun dispose() {
        scope?.cancel()
    }
}