package nl.avwie.kotlinx.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

interface IViewModel {
    fun dispose()
}

abstract class ViewModel(
    protected val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
): IViewModel {
    override fun dispose() {
        viewModelScope.cancel()
    }
}