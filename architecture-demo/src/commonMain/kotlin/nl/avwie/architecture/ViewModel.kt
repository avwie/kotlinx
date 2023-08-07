package nl.avwie.architecture

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow

interface ViewModel<S> {
    val state: StateFlow<S>
    val scope: CoroutineScope

    fun dispose() {
        scope.cancel()
    }
}