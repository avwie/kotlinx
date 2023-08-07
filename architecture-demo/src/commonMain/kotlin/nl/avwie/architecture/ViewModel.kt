package nl.avwie.architecture

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow


interface ViewModel {
    val scope: CoroutineScope

    fun dispose() {
        scope.cancel()
    }
}

interface StatefulViewModel<S> : ViewModel {
    val state: StateFlow<S>
}